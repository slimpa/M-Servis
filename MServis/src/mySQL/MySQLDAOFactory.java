package mySQL;

import dao.ProizvodjacDAO;
import dao.ZaposleniDAO;
import dao.ServisTelefonaHasCjenovnikUslugaDAO;
import dao.DAOFactory;
import dao.DobavljacDAO;
import dao.NarudzbaHasArtikalDAO;
import dao.RacunHasArtikalDAO;
import dao.TipDodatneOpremeDAO;
import dao.RacunHasServisTelefonaDAO;
import dao.RezervniDioDAO;
import dao.KlijentDAO;
import dao.ModelTelefonaDAO;
import dao.ServisTelefonaDAO;
import dao.CjenovnikUslugaDAO;
import dao.CijenaDAO;
import dao.OsobaDAO;
import dao.UgradjeniRezervniDioDAO;
import dao.StanjeTelefonaDAO;
import dao.RacunDAO;
import dao.IzvjestajDAO;
import dao.NarudzbaDAO;
import dao.AdminDAO;
import dao.ArtikalDAO;
import dao.DodatnaOpremaDAO;
import dao.TelefonDAO;

public class MySQLDAOFactory extends DAOFactory {

	public ArtikalDAO getArtikalDAO() {
		return new MySQLArtikalDAO();
	}

	public TelefonDAO getTelefonDAO() {
		return new MySQLTelefonDAO();
	}

	public DodatnaOpremaDAO getDodatnaOpremaDAO() {
		return new MySQLDodatnaOpremaDAO();
	}

	public RezervniDioDAO getRezervniDioDAO() {
		return new MySQLRezervniDioDAO();
	}

	public OsobaDAO getOsobaDAO() {
		return new MySQLOsobaDAO();
		
	}

	public ZaposleniDAO getZaposleniDAO() {
                return new MySQLZaposleniDAO();
	}

	public KlijentDAO getKlijentDAO() {
		// TODO - implement MySQLDAOFactory.getKlijentDAO
		throw new UnsupportedOperationException();
	}

	public RacunDAO getRacunDAO() {
		// TODO - implement MySQLDAOFactory.getRacunDAO
		throw new UnsupportedOperationException();
	}

	public IzvjestajDAO getIzvjestajDAO() {
		// TODO - implement MySQLDAOFactory.getIzvjestajDAO
		throw new UnsupportedOperationException();
	}

	public ServisTelefonaDAO getServisTelefonaDAO() {
		// TODO - implement MySQLDAOFactory.getServisTelefonaDAO
		throw new UnsupportedOperationException();
	}

	public AdminDAO getAdminDAO() {
                return new MySQLAdminDAO();
	}

	public DobavljacDAO getDobavljacDAO() {
		// TODO - implement MySQLDAOFactory.getDobavljacDAO
		throw new UnsupportedOperationException();
	}

	public ProizvodjacDAO getProizvodjacDAO() {
		return new MySQLProizvodjacDAO();
	}

	public ModelTelefonaDAO getModelTelefonaDAO() {
		// TODO - implement MySQLDAOFactory.getModelTelefonaDAO
		throw new UnsupportedOperationException();
	}

	public UgradjeniRezervniDioDAO getUgradjeniRezervniDioDAO() {
		// TODO - implement MySQLDAOFactory.getUgradjeniRezervniDioDAO
		throw new UnsupportedOperationException();
	}

	public RacunHasArtikalDAO getRacunHasArtikalDAO() {
		// TODO - implement MySQLDAOFactory.getRacunHasArtikalDAO
		throw new UnsupportedOperationException();
	}

	public NarudzbaDAO getNarudzbaDAO() {
		// TODO - implement MySQLDAOFactory.getNarudzbaDAO
		throw new UnsupportedOperationException();
	}

	public NarudzbaHasArtikalDAO getNarudzbaHasArtikalDAO() {
		// TODO - implement MySQLDAOFactory.getNarudzbaHasArtikalDAO
		throw new UnsupportedOperationException();
	}

	public TipDodatneOpremeDAO getTipDodatneOpremeDAO() {
            return new MySQLTipDodatneOpremeDAO();
	}

	public CjenovnikUslugaDAO getCjenovnikUslugaDAO() {
		return new MySQLCjenovnikUslugaDAO();
	}

	public StanjeTelefonaDAO getStanjeTelefonaDAO() {
		return new MySQLStanjeTelefonaDAO();
	}

	public ServisTelefonaHasCjenovnikUslugaDAO getServisTelefonaHasCjenovnikUslugaDAO() {
		// TODO - implement MySQLDAOFactory.getServisTelefonaHasCjenovnikUslugaDAO
		throw new UnsupportedOperationException();
	}

	public RacunHasServisTelefonaDAO getRacunHasServisTelefonaDAO() {
		// TODO - implement MySQLDAOFactory.getRacunHasServisTelefonaDAO
		throw new UnsupportedOperationException();
	}

	public CijenaDAO getCijenaDAO() {
		// TODO - implement MySQLDAOFactory.getCijenaDAO
		throw new UnsupportedOperationException();
	}

}