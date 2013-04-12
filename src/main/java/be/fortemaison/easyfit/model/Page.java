package be.fortemaison.easyfit.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 10/04/13
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public class Page<E> extends ArrayList<E> {

    public static int PAGE_SIZE = 25;


    private int pageSize = PAGE_SIZE;

    private int pageCount = 1;

    private int currentPage = 1;

    /**
     *
     */
    public Page () {
    }

    public Page (int initialCapacity) {
        super(initialCapacity);
    }

    public Page (Collection<? extends E> c) {
        super(c);
    }

    public Page (List<E> list) {
        this.addAll(list);
    }

    public int getPageSize () {
        return pageSize;
    }

    public void setPageSize (int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount () {
        return pageCount;
    }

    public void setPageCount (int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage () {
        return currentPage;
    }

    public void setCurrentPage (int currentPage) {
        this.currentPage = currentPage;
    }



}
