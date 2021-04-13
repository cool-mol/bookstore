package coolmol.ebookstore.controller;

import coolmol.ebookstore.entity.BookStatistic;
import coolmol.ebookstore.entity.UserStatistic;
import coolmol.ebookstore.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/statistic")
@CrossOrigin
public class ServiceController {

    @Autowired
    StatisticService statisticService;

    @GetMapping("/saleOfBook/{date1}/{date2}")
    List<BookStatistic> saleOfBook(@PathVariable("date1") Long date1,
                                   @PathVariable("date2") Long date2){
        return statisticService.getSaleOfBook(new Date(date1),new Date(date2));
    }

    @GetMapping("/saleOfUser/{date1}/{date2}")
    List<UserStatistic> saleOfUser(@PathVariable("date1") Long date1,
                                   @PathVariable("date2") Long date2){
        return statisticService.getSaleOfUser(new Date(date1),new Date(date2));
    }

    @GetMapping("/buyOfBook/{date1}/{date2}/{userID}")
    List<BookStatistic> saleOfBook(@PathVariable("date1") Long date1,
                                   @PathVariable("date2") Long date2,
                                   @PathVariable("userID") Integer userID){
        return statisticService.getBuyOfBook(new Date(date1),new Date(date2), userID);
    }
}
