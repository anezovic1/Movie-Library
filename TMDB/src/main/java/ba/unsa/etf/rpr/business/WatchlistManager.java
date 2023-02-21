package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Watchlist;
import ba.unsa.etf.rpr.exceptions.MovieException;

import java.util.List;

/**
 * Business Logic Layer for management of Watchlists
 *
 * @author Anida Nezovic
 */
public class WatchlistManager {
    public List<Watchlist> getAll() throws MovieException {
        return DaoFactory.watchlistDao().getAll();
    }

    public void delete(int watchlistID) throws MovieException {
        DaoFactory.watchlistDao().delete(watchlistID);
    }

    public Watchlist getById(int watchlistID) throws MovieException {
        return DaoFactory.watchlistDao().getById(watchlistID);
    }

    public void update(Watchlist watchlist) throws MovieException {
        DaoFactory.watchlistDao().update(watchlist);
    }

    public Watchlist add(Watchlist watchlist) throws MovieException {
        return DaoFactory.watchlistDao().add(watchlist);
    }
}
