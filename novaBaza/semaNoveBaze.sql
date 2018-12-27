-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema m:servis
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema m:servis
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `m:servis` DEFAULT CHARACTER SET utf8 ;
USE `m:servis` ;

-- -----------------------------------------------------
-- Table `m:servis`.`Osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Osoba` (
  `IdOsoba` INT NOT NULL,
  `Ime` VARCHAR(255) NOT NULL,
  `Prezime` VARCHAR(255) NOT NULL,
  `BrojTelefona` VARCHAR(255) NULL,
  PRIMARY KEY (`IdOsoba`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Admin` (
  `IdAdmin` INT NOT NULL,
  `NazivFirme` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdAdmin`),
  CONSTRAINT `fk_Admin_Osoba`
    FOREIGN KEY (`IdAdmin`)
    REFERENCES `m:servis`.`Osoba` (`IdOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Klijent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Klijent` (
  `IdKlijent` INT NOT NULL,
  PRIMARY KEY (`IdKlijent`),
  CONSTRAINT `fk_Klijent_Osoba1`
    FOREIGN KEY (`IdKlijent`)
    REFERENCES `m:servis`.`Osoba` (`IdOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Dobavljac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Dobavljac` (
  `IdDobavljac` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Adresa` VARCHAR(255) NOT NULL,
  `Telefon` VARCHAR(255) NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Fax` VARCHAR(255) NULL,
  PRIMARY KEY (`IdDobavljac`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Zaposleni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Zaposleni` (
  `IdZaposleni` INT NOT NULL,
  `RadnoMjesto` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdZaposleni`),
  CONSTRAINT `fk_Zaposleni_Osoba1`
    FOREIGN KEY (`IdZaposleni`)
    REFERENCES `m:servis`.`Osoba` (`IdOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Racun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Racun` (
  `IdRacun` INT NOT NULL,
  `Vrijeme` DATETIME NOT NULL,
  `UkupnaCijena` DECIMAL(10,2) NOT NULL,
  `IdIzdavac` INT NOT NULL,
  PRIMARY KEY (`IdRacun`),
  INDEX `fk_Racun_Zaposleni1_idx` (`IdIzdavac` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_Zaposleni1`
    FOREIGN KEY (`IdIzdavac`)
    REFERENCES `m:servis`.`Zaposleni` (`IdZaposleni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Proizvodjac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Proizvodjac` (
  `IdProizvodjac` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdProizvodjac`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Artikal` (
  `IdArtikal` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Cijena` DECIMAL(10,2) NOT NULL,
  `IdProizvodjac` INT NOT NULL,
  PRIMARY KEY (`IdArtikal`),
  INDEX `fk_Artikal_Proizvodjac1_idx` (`IdProizvodjac` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_Proizvodjac1`
    FOREIGN KEY (`IdProizvodjac`)
    REFERENCES `m:servis`.`Proizvodjac` (`IdProizvodjac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Racun_has_Artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Racun_has_Artikal` (
  `IdRacun` INT NOT NULL,
  `IdArtikal` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  PRIMARY KEY (`IdRacun`, `IdArtikal`),
  INDEX `fk_Artikal_has_Racun_Racun1_idx` (`IdRacun` ASC) VISIBLE,
  INDEX `fk_Artikal_has_Racun_Artikal1_idx` (`IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_has_Racun_Artikal1`
    FOREIGN KEY (`IdArtikal`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_has_Racun_Racun1`
    FOREIGN KEY (`IdRacun`)
    REFERENCES `m:servis`.`Racun` (`IdRacun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`ModelTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`ModelTelefona` (
  `IdModelTelefona` INT NOT NULL,
  `NazivModela` VARCHAR(255) NOT NULL,
  `Slika` VARCHAR(255) NULL,
  `Specifikacija` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdModelTelefona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Telefon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Telefon` (
  `IdTelefon` INT NOT NULL,
  `SerijskiBroj` VARCHAR(255) NOT NULL,
  `DatumProizvodnje` DATE NULL,
  `Boja` VARCHAR(255) NOT NULL,
  `IdModelTelefona` INT NOT NULL,
  PRIMARY KEY (`IdTelefon`),
  INDEX `fk_Telefon_ModelTelefona1_idx` (`IdModelTelefona` ASC) VISIBLE,
  UNIQUE INDEX `SerijskiBroj_UNIQUE` (`SerijskiBroj` ASC) VISIBLE,
  CONSTRAINT `fk_Telefon_Artikal1`
    FOREIGN KEY (`IdTelefon`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Telefon_ModelTelefona1`
    FOREIGN KEY (`IdModelTelefona`)
    REFERENCES `m:servis`.`ModelTelefona` (`IdModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`RezervniDio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`RezervniDio` (
  `IdRezervniDio` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  `Opis` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdRezervniDio`),
  CONSTRAINT `fk_RezervniDio_Artikal1`
    FOREIGN KEY (`IdRezervniDio`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`TipDodatneOpreme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`TipDodatneOpreme` (
  `IdTipDodatneOpreme` INT NOT NULL,
  `TipOpreme` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdTipDodatneOpreme`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`DodatnaOprema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`DodatnaOprema` (
  `IdDodatnaOprema` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  `Boja` VARCHAR(255) NOT NULL,
  `IdModelTelefona` INT NOT NULL,
  `IdTipDodatneOpreme` INT NOT NULL,
  PRIMARY KEY (`IdDodatnaOprema`),
  INDEX `fk_DodatnaOprema_ModelTelefona1_idx` (`IdModelTelefona` ASC) VISIBLE,
  INDEX `fk_DodatnaOprema_TipDodatneOpreme1_idx` (`IdTipDodatneOpreme` ASC) VISIBLE,
  CONSTRAINT `fk_DodatnaOprema_Artikal1`
    FOREIGN KEY (`IdDodatnaOprema`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DodatnaOprema_ModelTelefona1`
    FOREIGN KEY (`IdModelTelefona`)
    REFERENCES `m:servis`.`ModelTelefona` (`IdModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DodatnaOprema_TipDodatneOpreme1`
    FOREIGN KEY (`IdTipDodatneOpreme`)
    REFERENCES `m:servis`.`TipDodatneOpreme` (`IdTipDodatneOpreme`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`ModelTelefona_has_RezervniDio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`ModelTelefona_has_RezervniDio` (
  `IdModelTelefona` INT NOT NULL,
  `IdRezervniDio` INT NOT NULL,
  PRIMARY KEY (`IdModelTelefona`, `IdRezervniDio`),
  INDEX `fk_ModelTelefona_has_RezervniDio_RezervniDio1_idx` (`IdRezervniDio` ASC) VISIBLE,
  INDEX `fk_ModelTelefona_has_RezervniDio_ModelTelefona1_idx` (`IdModelTelefona` ASC) VISIBLE,
  CONSTRAINT `fk_ModelTelefona_has_RezervniDio_ModelTelefona1`
    FOREIGN KEY (`IdModelTelefona`)
    REFERENCES `m:servis`.`ModelTelefona` (`IdModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ModelTelefona_has_RezervniDio_RezervniDio1`
    FOREIGN KEY (`IdRezervniDio`)
    REFERENCES `m:servis`.`RezervniDio` (`IdRezervniDio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Narudzba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Narudzba` (
  `IdNarudzba` INT NOT NULL,
  `DatumNarudzbe` DATETIME NOT NULL,
  `IdDobavljac` INT NOT NULL,
  PRIMARY KEY (`IdNarudzba`),
  INDEX `fk_Narudzba_Dobavljac1_idx` (`IdDobavljac` ASC) VISIBLE,
  CONSTRAINT `fk_Narudzba_Dobavljac1`
    FOREIGN KEY (`IdDobavljac`)
    REFERENCES `m:servis`.`Dobavljac` (`IdDobavljac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Narudzba_has_Artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Narudzba_has_Artikal` (
  `IdNarudzba` INT NOT NULL,
  `IdArtikal` INT NOT NULL,
  PRIMARY KEY (`IdNarudzba`, `IdArtikal`),
  INDEX `fk_Narudzba_has_Artikal_Artikal1_idx` (`IdArtikal` ASC) VISIBLE,
  INDEX `fk_Narudzba_has_Artikal_Narudzba1_idx` (`IdNarudzba` ASC) VISIBLE,
  CONSTRAINT `fk_Narudzba_has_Artikal_Narudzba1`
    FOREIGN KEY (`IdNarudzba`)
    REFERENCES `m:servis`.`Narudzba` (`IdNarudzba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_has_Artikal_Artikal1`
    FOREIGN KEY (`IdArtikal`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Izvjestaj` (
  `IdIzvjestaj` INT NOT NULL,
  `DatumIzdavanja` VARCHAR(255) NOT NULL,
  `Opis` TEXT(1000) NULL,
  `Putanja` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdIzvjestaj`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Artikal_has_Izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Artikal_has_Izvjestaj` (
  `IdArtikal` INT NOT NULL,
  `IdIzvjestaj` INT NOT NULL,
  PRIMARY KEY (`IdArtikal`, `IdIzvjestaj`),
  INDEX `fk_Artikal_has_Izvjestaj_Izvjestaj1_idx` (`IdIzvjestaj` ASC) VISIBLE,
  INDEX `fk_Artikal_has_Izvjestaj_Artikal1_idx` (`IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_has_Izvjestaj_Artikal1`
    FOREIGN KEY (`IdArtikal`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_has_Izvjestaj_Izvjestaj1`
    FOREIGN KEY (`IdIzvjestaj`)
    REFERENCES `m:servis`.`Izvjestaj` (`IdIzvjestaj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`StanjeTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`StanjeTelefona` (
  `IdStanjeTelefona` INT NOT NULL,
  `Stanje` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`IdStanjeTelefona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`CijenovnikUsluga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`CijenovnikUsluga` (
  `IdCijenovnikUsluga` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Kolicina` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`IdCijenovnikUsluga`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`ServisTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`ServisTelefona` (
  `IdServisTelefona` INT NOT NULL,
  `IdKlijent` INT NOT NULL,
  `OpisKvara` VARCHAR(255) NOT NULL,
  `DatumPrijema` DATETIME NOT NULL,
  `SerijskiBrojTelefona` VARCHAR(255) NOT NULL,
  `IdCijenovnikUsluga` INT NOT NULL,
  `IdStanjeTelefona` INT NOT NULL,
  `IdZaposleni` INT NOT NULL,
  `IdModelTelefona` INT NOT NULL,
  PRIMARY KEY (`IdServisTelefona`),
  INDEX `fk_ServisTelefona_CijenovnikUsluga1_idx` (`IdCijenovnikUsluga` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_StanjeTelefona1_idx` (`IdStanjeTelefona` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_Zaposleni1_idx` (`IdZaposleni` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_ModelTelefona1_idx` (`IdModelTelefona` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_Klijent1_idx` (`IdKlijent` ASC) VISIBLE,
  CONSTRAINT `fk_ServisTelefona_CijenovnikUsluga1`
    FOREIGN KEY (`IdCijenovnikUsluga`)
    REFERENCES `m:servis`.`CijenovnikUsluga` (`IdCijenovnikUsluga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_StanjeTelefona1`
    FOREIGN KEY (`IdStanjeTelefona`)
    REFERENCES `m:servis`.`StanjeTelefona` (`IdStanjeTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_Zaposleni1`
    FOREIGN KEY (`IdZaposleni`)
    REFERENCES `m:servis`.`Zaposleni` (`IdZaposleni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_ModelTelefona1`
    FOREIGN KEY (`IdModelTelefona`)
    REFERENCES `m:servis`.`ModelTelefona` (`IdModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_Klijent1`
    FOREIGN KEY (`IdKlijent`)
    REFERENCES `m:servis`.`Klijent` (`IdKlijent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Artikal_has_Dobavljac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Artikal_has_Dobavljac` (
  `IdArtikal` INT NOT NULL,
  `IdDobavljac` INT NOT NULL,
  PRIMARY KEY (`IdArtikal`, `IdDobavljac`),
  INDEX `fk_Artikal_has_Dobavljac_Dobavljac1_idx` (`IdDobavljac` ASC) VISIBLE,
  INDEX `fk_Artikal_has_Dobavljac_Artikal1_idx` (`IdArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_has_Dobavljac_Artikal1`
    FOREIGN KEY (`IdArtikal`)
    REFERENCES `m:servis`.`Artikal` (`IdArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_has_Dobavljac_Dobavljac1`
    FOREIGN KEY (`IdDobavljac`)
    REFERENCES `m:servis`.`Dobavljac` (`IdDobavljac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`Racun_has_ServisTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`Racun_has_ServisTelefona` (
  `IdRacun` INT NOT NULL,
  `IdServisTelefona` INT NOT NULL,
  PRIMARY KEY (`IdRacun`, `IdServisTelefona`),
  INDEX `fk_Racun_has_ServisTelefona_ServisTelefona1_idx` (`IdServisTelefona` ASC) VISIBLE,
  INDEX `fk_Racun_has_ServisTelefona_Racun1_idx` (`IdRacun` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_has_ServisTelefona_Racun1`
    FOREIGN KEY (`IdRacun`)
    REFERENCES `m:servis`.`Racun` (`IdRacun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Racun_has_ServisTelefona_ServisTelefona1`
    FOREIGN KEY (`IdServisTelefona`)
    REFERENCES `m:servis`.`ServisTelefona` (`IdServisTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `m:servis`.`ServisTelefona_has_RezervniDio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `m:servis`.`ServisTelefona_has_RezervniDio` (
  `IdServisTelefona` INT NOT NULL,
  `IdRezervniDio` INT NOT NULL,
  PRIMARY KEY (`IdServisTelefona`, `IdRezervniDio`),
  INDEX `fk_ServisTelefona_has_RezervniDio_RezervniDio1_idx` (`IdRezervniDio` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_has_RezervniDio_ServisTelefona1_idx` (`IdServisTelefona` ASC) VISIBLE,
  CONSTRAINT `fk_ServisTelefona_has_RezervniDio_ServisTelefona1`
    FOREIGN KEY (`IdServisTelefona`)
    REFERENCES `m:servis`.`ServisTelefona` (`IdServisTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_has_RezervniDio_RezervniDio1`
    FOREIGN KEY (`IdRezervniDio`)
    REFERENCES `m:servis`.`RezervniDio` (`IdRezervniDio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
