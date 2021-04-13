package coolmol.ebookstore.serviceimpl;

import coolmol.ebookstore.service.CounterService;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {
  int c = 0;
  @Override
  public synchronized void increment(){
    System.out.println("increment");
    c++;
  }
  @Override
  public synchronized void decrement(){
    System.out.println("decrement");
    c--;
  }
  @Override
  public synchronized int value(){
    System.out.println("value");
    return c;
  }
}
