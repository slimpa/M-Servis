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
		return new MySQLKlijentDAO();
	}

	public RacunDAO getRacunDAO() {
		return new MySQLRacunDAO();
	}

	public IzvjestajDAO getIzvjestajDAO() {
		return new MySQLIzvjestajDAO();
	}

	public ServisTelefonaDAO getServisTelefonaDAO() {
            return new MySQLServisTelefonaDAO();
	}

	public AdminDAO getAdminDAO() {
                return new MySQLAdminDAO();
	}

	public DobavljacDAO getDobavljacDAO() {
		return new MySQLDobavljacDAO();
	}

	public ProizvodjacDAO getProizvodjacDAO() {
		return new MySQLProizvodjacDAO();
	}

	public ModelTelefonaDAO getModelTelefonaDAO() {
		return new MySQLModelTelefonDAO();
	}

	public UgradjeniRezervniDioDAO getUgradjeniRezervniDioDAO() {
		return new MySQLUgradjeniRezervniDioDAO();
	}

	public RacunHasArtikalDAO getRacunHasArtikalDAO() {
		return new MySQLRacunHasArtikalDAO();
	}

	public NarudzbaDAO getNarudzbaDAO() {
		return new MySQLNarudzbaDAO();
	}

	public NarudzbaHasArtikalDAO getNarudzbaHasArtikalDAO() {
		return new MySQLNarudzbaHasArtikalDAO();
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
		return new MySQLServisTelefonaHasCjenovnikUslugaDAO();
	}

	public RacunHasServisTelefonaDAO getRacunHasServisTelefonaDAO() {
		return new MySQLRacunHasServisTelefonaDAO();
	}

	public CijenaDAO getCijenaDAO() {
		return new MySQLCijenaDAO();
	}

}