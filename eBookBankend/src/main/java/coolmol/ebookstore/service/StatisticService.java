package coolmol.ebookstore.service;

import coolmol.ebookstore.entity.BookStatistic;
import coolmol.ebookstore.entity.UserStatistic;

import java.util.Date;
import java.util.List;

public interface StatisticService {
    List<BookStatistic> getSaleOfBook(Date date1, Date date2);
    List<UserStatistic> getSaleOfUser(Date date1, Date date2);
    List<BookStatistic> getBuyOfBook(Date date1, Date date2, Integer userID);
}
