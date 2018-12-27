package dao;

import dto.UgradjeniRezervniDioDTO;

public abstract class DAOFactory {

	public abstract ArtikalDAO getArtikalDAO();

	public abstract TelefonDAO getTelefonDAO();

	public abstract DodatnaOpremaDAO getDodatnaOpremaDAO();

	public abstract RezervniDioDAO getRezervniDioDAO();

	public abstract OsobaDAO getOsobaDAO();

	public abstract ZaposleniDAO getZaposleniDAO();

	public abstract KlijentDAO getKlijentDAO();

	public abstract RacunDAO getRacunDAO();

	public abstract IzvjestajDAO getIzvjestajDAO();

	public abstract ServisTelefonaDAO getServisTelefonaDAO();

	public abstract AdminDAO getAdminDAO();

	public abstract DobavljacDAO getDobavljacDAO();

	public abstract ProizvodjacDAO getProizvodjacDAO();

	public abstract ModelTelefonaDAO getModelTelefonaDAO();

	public abstract UgradjeniRezervniDioDAO getUgradjeniRezervniDioDAO();

	public abstract ArtikalHasDobavljacDAO getArtikalHasDobavljacDAO();

	public abstract RacunHasArtikalDAO getRacunHasArtikalDAO();

	public abstract NarudzbaDAO getNarudzbaDAO();

	public abstract NarudzbaHasArtikalDAO getNarudzbaHasArtikalDAO();

	public abstract ModelTelefonHasRezervniDioDAO getModelTelefonaHasRezervniDioDAO();

	public abstract TipDodatneOpremeDAO getTipDodatneOpremeDAO();

	public abstract CjenovnikUslugaDAO getCjenovnikUslugaDAO();

	public abstract StanjeTelefonaDAO getStanjeTelefonaDAO();

	public abstract ArtikalHasIzvjestajDAO getArtikalHasIzvjestaj();

	public abstract ServisTelefonaHasIzvjestajDAO getServisTelefonaHasIzvjestajDAO();

	public abstract RacunHasServisTelefonaDAO getRacunHasServisTelefonaDAO();

}