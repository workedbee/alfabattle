package com.alfa.alfabattle.bargaining.services;

import com.alfa.alfabattle.bargaining.model.Branch;
import com.alfa.alfabattle.bargaining.model.QueueWait;
import com.alfa.alfabattle.bargaining.repository.QueueWaitRepository;
import org.apache.commons.math3.stat.descriptive.rank.Median;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadPredictor {
    private static final Logger LOG = LoggerFactory.getLogger(LoadPredictor.class);

    private QueueWaitRepository queueWaitRepository;

    @Autowired
    public LoadPredictor(
            QueueWaitRepository queueWaitRepository) {
        this.queueWaitRepository = queueWaitRepository;
    }

    /**
     * Predict time in queue in seconds.
     */
    public int predictTime(Branch branch, int dayOfWeek, int hourOfDay) {
        List<QueueWait> queueWaits = queueWaitRepository.findByBranchId(branch.getId());
        List<Double> diffs = queueWaits
                .stream()
                .filter(wait -> getDayOfWeek(wait.getData()) == dayOfWeek)
                .filter(wait -> isHourOfDayInWaitPeriod(wait, hourOfDay))
                .map(wait -> getDifferenceInSecond(wait.getEnd(), wait.getStart()))
                .map(Long::doubleValue)
                .collect(Collectors.toList());

        double[] periods = diffs.stream()
                .mapToDouble(Double::doubleValue)
                .toArray();

        double median = new Median().evaluate(periods);
        return (int)median;
    }

    private int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        return index == Calendar.SUNDAY
                ? 7
                : index - 1;
    }

    private boolean isHourOfDayInWaitPeriod(QueueWait wait, int hourOfDay) {
        int start = getHourOfDay(wait.getStart());
        int end = getHourOfDay(wait.getEnd());
        //return hourOfDay >= start && hourOfDay <= end;
        return hourOfDay == end;
    }

    private int getHourOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Return time difference in seconds.
     */
    private long getDifferenceInSecond(Date x, Date y) {
        return (x.getTime()-y.getTime())/1000;
    }

}
