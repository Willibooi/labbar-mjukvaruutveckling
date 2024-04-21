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
 * @author 22wili03
 */
public class SiteService {
    SiteDao dao = new SiteDao();
    
    public SiteService() {
        
    }
    
    public void save(Site p) {
        dao.save(p);
    }
    
    public Site get(int i) {
        return dao.get(i);
    }
    
    public List<Site> getAll() {
        return dao.getAll();
    }
    
    public void update(Site p) {
        dao.update(p);
    }
    
    public void delete(Site p) {
        dao.delete(p);
    }
}
