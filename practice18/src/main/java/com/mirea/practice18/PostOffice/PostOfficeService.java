package com.mirea.practice18.PostOffice;

import java.util.List;

public interface PostOfficeService {
    List<PostOffice> getAll(String name, String cityName);

    PostOffice getById(Long id);

    void add(PostOffice postOffice);

    boolean removeById(Long id);

}
