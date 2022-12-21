package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.WatchlistDao;
import ba.unsa.etf.rpr.dao.WatchlistDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Watchlist;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        WatchlistDao dao = new WatchlistDaoSQLImpl();
        List<Watchlist> watchlists = dao.getAll();
        System.out.println(watchlists);

        /* I inserted an item Watchlist{id=1, name='Horror'} with query written in MySQL Workbench
        and now I want to delete that item.
         */

        dao.delete(1);
        watchlists = dao.getAll();
        System.out.println(watchlists);

    }
}
