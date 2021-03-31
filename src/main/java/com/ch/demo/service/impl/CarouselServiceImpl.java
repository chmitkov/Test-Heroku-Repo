package com.ch.demo.service.impl;

import com.ch.demo.service.CarouselService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CarouselServiceImpl implements CarouselService {

  private static Logger LOGGER = LoggerFactory.getLogger(CarouselServiceImpl.class);

  private List<String> images;

  public CarouselServiceImpl(@Value("${carousel.images}") List<String> images) {
    this.images = images;
  }

  @PostConstruct
  private void checkImages() {
    if (images.size() < 3) {
      throw new IllegalStateException("Please configure at least three images for the carousel!");
    }
    Collections.shuffle(this.images);
  }

  @Override
  public String getFirst() {
    return getAt(0);
  }

  @Override
  public String getSecond() {
    return getAt(1);
  }

  @Override
  public String getThird() {
    return getAt(2);
  }

  private String getAt(int position) {
    return images.get(position);
  }

  @Scheduled(cron = "${carousel.cron}")
  public void shuffle() {
    LOGGER.info("Shuffling the carousel...");
    Collections.shuffle(images);
  }

}
