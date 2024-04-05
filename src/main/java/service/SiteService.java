/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import domain.Site;
import java.util.List;
import repository.SiteDao;

/**
 *
 * @author GTSA - Infinity
 */
public class SiteService {
    SiteDao dao = new SiteDao();
    
    public SiteService() {
        this(new SiteDao());
    }
    public SiteService(SiteDao siteDao) {
        dao = siteDao;
    }
    
    public void save(Site p) {
        if(p == null) {
            throw new NullPointerException("Cant save null");
        }
        dao.save(p);
    }
    
    public Site get(int i) {
        return dao.get(i);
    }
    
    public List<Site> getAll() {
        return dao.getAll();
    }
    
    public void update(Site p) {
        if(p == null) {
            throw new NullPointerException("Cant update null");
        }
        dao.update(p);
    }
    
    public void delete(Site p) {
        if(p == null) {
            throw new NullPointerException("Cant delete null");
        }
        dao.delete(p);
    }
}
