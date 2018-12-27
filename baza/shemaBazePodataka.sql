-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema mservis
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mservis
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mservis` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Osoba` (
  `idOsoba` INT NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(255) NOT NULL,
  `Prezime` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idOsoba`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Zaposleni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Zaposleni` (
  `idOsoba_Zaposleni` INT NOT NULL,
  `RadnoMjesto` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idOsoba_Zaposleni`),
  CONSTRAINT `fk_Zaposleni_Osoba`
    FOREIGN KEY (`idOsoba_Zaposleni`)
    REFERENCES `mydb`.`Osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Klijent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Klijent` (
  `idOsoba_Klijent` INT NOT NULL,
  PRIMARY KEY (`idOsoba_Klijent`),
  CONSTRAINT `fk_Klijent_Osoba1`
    FOREIGN KEY (`idOsoba_Klijent`)
    REFERENCES `mydb`.`Osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Admin` (
  `NazivFirme` VARCHAR(255) NOT NULL,
  `idOsoba_Admin` INT NOT NULL,
  PRIMARY KEY (`idOsoba_Admin`),
  CONSTRAINT `fk_Admin_Osoba1`
    FOREIGN KEY (`idOsoba_Admin`)
    REFERENCES `mydb`.`Osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Proizvodjac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Proizvodjac` (
  `idProizvodjac` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idProizvodjac`),
  UNIQUE INDEX `Naziv_UNIQUE` (`Naziv` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Artikal` (
  `idArtikal` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Cijena` DECIMAL(2) NOT NULL,
  `Proizvodjac_idProizvodjac` INT NOT NULL,
  PRIMARY KEY (`idArtikal`),
  INDEX `fk_Artikal_Proizvodjac1_idx` (`Proizvodjac_idProizvodjac` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_Proizvodjac1`
    FOREIGN KEY (`Proizvodjac_idProizvodjac`)
    REFERENCES `mydb`.`Proizvodjac` (`idProizvodjac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`ModelTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`ModelTelefona` (
  `idModelTelefona` INT NOT NULL,
  `Specifikacija` VARCHAR(255) NOT NULL,
  `Slika` BLOB NULL,
  `NazivModela` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idModelTelefona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Telefon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Telefon` (
  `idTelefon` INT NOT NULL AUTO_INCREMENT,
  `SerijskiBroj` VARCHAR(255) NOT NULL,
  `DatumProizvodnje` DATE NULL,
  `Boja` VARCHAR(255) NOT NULL,
  `ModelTelefona_idModelTelefona` INT NOT NULL,
  PRIMARY KEY (`idTelefon`),
  INDEX `fk_Telefon_Artikal1_idx` (`idTelefon` ASC) VISIBLE,
  UNIQUE INDEX `SerijskiBroj_UNIQUE` (`SerijskiBroj` ASC) VISIBLE,
  INDEX `fk_Telefon_ModelTelefona1_idx` (`ModelTelefona_idModelTelefona` ASC) VISIBLE,
  CONSTRAINT `fk_Telefon_Artikal1`
    FOREIGN KEY (`idTelefon`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Telefon_ModelTelefona1`
    FOREIGN KEY (`ModelTelefona_idModelTelefona`)
    REFERENCES `mservis`.`ModelTelefona` (`idModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RezervniDio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RezervniDio` (
  `idRezervniDio` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  `Opis` VARCHAR(255) NULL,
  PRIMARY KEY (`idRezervniDio`),
  INDEX `fk_RezervniDio_Artikal1_idx` (`idRezervniDio` ASC) VISIBLE,
  CONSTRAINT `fk_RezervniDio_Artikal1`
    FOREIGN KEY (`idRezervniDio`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`TipDodatneOpreme`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`TipDodatneOpreme` (
  `idTipDodatneOpreme` INT NOT NULL,
  `Tip` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idTipDodatneOpreme`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DodatnaOprema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DodatnaOprema` (
  `idDodatnaOprema` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  `ModelTelefona_idModelTelefona` INT NOT NULL,
  `TipDodatneOpreme_idTipDodatneOpreme` INT NOT NULL,
  `Boja` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idDodatnaOprema`),
  INDEX `fk_DodatnaOprema_Artikal1_idx` (`idDodatnaOprema` ASC) VISIBLE,
  INDEX `fk_DodatnaOprema_ModelTelefona1_idx` (`ModelTelefona_idModelTelefona` ASC) VISIBLE,
  INDEX `fk_DodatnaOprema_TipDodatneOpreme1_idx` (`TipDodatneOpreme_idTipDodatneOpreme` ASC) VISIBLE,
  CONSTRAINT `fk_DodatnaOprema_Artikal1`
    FOREIGN KEY (`idDodatnaOprema`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DodatnaOprema_ModelTelefona1`
    FOREIGN KEY (`ModelTelefona_idModelTelefona`)
    REFERENCES `mservis`.`ModelTelefona` (`idModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DodatnaOprema_TipDodatneOpreme1`
    FOREIGN KEY (`TipDodatneOpreme_idTipDodatneOpreme`)
    REFERENCES `mservis`.`TipDodatneOpreme` (`idTipDodatneOpreme`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Racun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Racun` (
  `idRacun` INT NOT NULL,
  `Vrijeme` DATETIME NULL,
  `UkupnaCijena` INT NOT NULL,
  `idIzdavaca` INT NOT NULL,
  PRIMARY KEY (`idRacun`),
  INDEX `fk_Racun_Zaposleni1_idx` (`idIzdavaca` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_Zaposleni1`
    FOREIGN KEY (`idIzdavaca`)
    REFERENCES `mydb`.`Zaposleni` (`idOsoba_Zaposleni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Racun_has_Artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Racun_has_Artikal` (
  `Racun_idRacun` INT NOT NULL,
  `Artikal_idArtikal` INT NOT NULL,
  `Kolicina` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`Racun_idRacun`, `Artikal_idArtikal`),
  INDEX `fk_Racun_has_Artikal_Artikal1_idx` (`Artikal_idArtikal` ASC) VISIBLE,
  INDEX `fk_Racun_has_Artikal_Racun1_idx` (`Racun_idRacun` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_has_Artikal_Racun1`
    FOREIGN KEY (`Racun_idRacun`)
    REFERENCES `mydb`.`Racun` (`idRacun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Racun_has_Artikal_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`CijenovnikUsluga`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`CijenovnikUsluga` (
  `idCijenovnikUsluga` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Cijena` DECIMAL(2) NOT NULL,
  PRIMARY KEY (`idCijenovnikUsluga`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`StanjeTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`StanjeTelefona` (
  `idStanjeTelefona` INT NOT NULL,
  `Stanje` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idStanjeTelefona`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ServisTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ServisTelefona` (
  `idServisa` INT NOT NULL,
  `Klijent_idOsoba_Klijent` INT NOT NULL,
  `OpisKvara` VARCHAR(255) NULL,
  `DatumPrijema` DATETIME NOT NULL,
  `DatumPreuzimanja` DATETIME NULL,
  `Zaposleni_idOsoba_Zaposleni` INT NOT NULL,
  `CijenovnikUsluga_idCijenovnikUsluga` INT NOT NULL,
  `Model` VARCHAR(255) NOT NULL,
  `SerijskiBrojTelefona` VARCHAR(255) NOT NULL,
  `StanjeTelefona_idStanjeTelefona` INT NOT NULL,
  PRIMARY KEY (`idServisa`, `Klijent_idOsoba_Klijent`),
  INDEX `fk_ServisTelefona_Zaposleni1_idx` (`Zaposleni_idOsoba_Zaposleni` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_CijenovnikUsluga1_idx` (`CijenovnikUsluga_idCijenovnikUsluga` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_Klijent1_idx` (`Klijent_idOsoba_Klijent` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_StanjeTelefona1_idx` (`StanjeTelefona_idStanjeTelefona` ASC) VISIBLE,
  CONSTRAINT `fk_ServisTelefona_Zaposleni1`
    FOREIGN KEY (`Zaposleni_idOsoba_Zaposleni`)
    REFERENCES `mydb`.`Zaposleni` (`idOsoba_Zaposleni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_CijenovnikUsluga1`
    FOREIGN KEY (`CijenovnikUsluga_idCijenovnikUsluga`)
    REFERENCES `mservis`.`CijenovnikUsluga` (`idCijenovnikUsluga`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_Klijent1`
    FOREIGN KEY (`Klijent_idOsoba_Klijent`)
    REFERENCES `mydb`.`Klijent` (`idOsoba_Klijent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_StanjeTelefona1`
    FOREIGN KEY (`StanjeTelefona_idStanjeTelefona`)
    REFERENCES `mservis`.`StanjeTelefona` (`idStanjeTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Izvjestaj` (
  `idIzvjestaj` INT NOT NULL,
  `Opis` VARCHAR(255) NULL,
  `DatumIzdavanja` DATETIME NOT NULL,
  `Putanja` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idIzvjestaj`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Artikal_has_Izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Artikal_has_Izvjestaj` (
  `Artikal_idArtikal` INT NOT NULL,
  `Izvjestaj_idIzvjestaj` INT NOT NULL,
  PRIMARY KEY (`Artikal_idArtikal`, `Izvjestaj_idIzvjestaj`),
  INDEX `fk_Artikal_has_Izvjestaj_Izvjestaj1_idx` (`Izvjestaj_idIzvjestaj` ASC) VISIBLE,
  INDEX `fk_Artikal_has_Izvjestaj_Artikal1_idx` (`Artikal_idArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_has_Izvjestaj_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_has_Izvjestaj_Izvjestaj1`
    FOREIGN KEY (`Izvjestaj_idIzvjestaj`)
    REFERENCES `mydb`.`Izvjestaj` (`idIzvjestaj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ServisTelefona_has_Izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ServisTelefona_has_Izvjestaj` (
  `ServisTelefona_idServisa` INT NOT NULL,
  `Izvjestaj_idIzvjestaj` INT NOT NULL,
  PRIMARY KEY (`ServisTelefona_idServisa`, `Izvjestaj_idIzvjestaj`),
  INDEX `fk_ServisTelefona_has_Izvjestaj_Izvjestaj1_idx` (`Izvjestaj_idIzvjestaj` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_has_Izvjestaj_ServisTelefona1_idx` (`ServisTelefona_idServisa` ASC) VISIBLE,
  CONSTRAINT `fk_ServisTelefona_has_Izvjestaj_ServisTelefona1`
    FOREIGN KEY (`ServisTelefona_idServisa`)
    REFERENCES `mydb`.`ServisTelefona` (`idServisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_has_Izvjestaj_Izvjestaj1`
    FOREIGN KEY (`Izvjestaj_idIzvjestaj`)
    REFERENCES `mydb`.`Izvjestaj` (`idIzvjestaj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UgradjeniRezervniDio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`UgradjeniRezervniDio` (
  `RezervniDio_idRezervniDio` INT NOT NULL,
  `ServisTelefona_idServisa` INT NOT NULL,
  PRIMARY KEY (`RezervniDio_idRezervniDio`, `ServisTelefona_idServisa`),
  INDEX `fk_UgradjeniRezervniDio_RezervniDio1_idx` (`RezervniDio_idRezervniDio` ASC) VISIBLE,
  INDEX `fk_UgradjeniRezervniDio_ServisTelefona1_idx` (`ServisTelefona_idServisa` ASC) VISIBLE,
  CONSTRAINT `fk_UgradjeniRezervniDio_RezervniDio1`
    FOREIGN KEY (`RezervniDio_idRezervniDio`)
    REFERENCES `mydb`.`RezervniDio` (`idRezervniDio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UgradjeniRezervniDio_ServisTelefona1`
    FOREIGN KEY (`ServisTelefona_idServisa`)
    REFERENCES `mydb`.`ServisTelefona` (`idServisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Racun_has_ServisTelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Racun_has_ServisTelefona` (
  `Racun_idRacun` INT NOT NULL,
  `ServisTelefona_idServisa` INT NOT NULL,
  PRIMARY KEY (`Racun_idRacun`, `ServisTelefona_idServisa`),
  INDEX `fk_Racun_has_ServisTelefona_ServisTelefona1_idx` (`ServisTelefona_idServisa` ASC) VISIBLE,
  INDEX `fk_Racun_has_ServisTelefona_Racun1_idx` (`Racun_idRacun` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_has_ServisTelefona_Racun1`
    FOREIGN KEY (`Racun_idRacun`)
    REFERENCES `mydb`.`Racun` (`idRacun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Racun_has_ServisTelefona_ServisTelefona1`
    FOREIGN KEY (`ServisTelefona_idServisa`)
    REFERENCES `mydb`.`ServisTelefona` (`idServisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mservis` ;

-- -----------------------------------------------------
-- Table `mservis`.`osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`osoba` (
  `idOsoba` INT(11) NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(255) NOT NULL,
  `Prezime` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idOsoba`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`admin` (
  `NazivFirme` VARCHAR(255) NOT NULL,
  `idOsoba_Admin` INT(11) NOT NULL,
  PRIMARY KEY (`idOsoba_Admin`),
  CONSTRAINT `fk_Admin_Osoba1`
    FOREIGN KEY (`idOsoba_Admin`)
    REFERENCES `mservis`.`osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`proizvodjac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`proizvodjac` (
  `idProizvodjac` INT(11) NOT NULL,
  `Naziv` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idProizvodjac`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`artikal` (
  `idArtikal` INT(11) NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Cijena` DECIMAL(2,0) NULL DEFAULT NULL,
  `Kolicina` INT(11) NULL DEFAULT NULL,
  `Proizvodjac_idProizvodjac` INT(11) NOT NULL,
  PRIMARY KEY (`idArtikal`),
  INDEX `fk_Artikal_Proizvodjac1_idx` (`Proizvodjac_idProizvodjac` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_Proizvodjac1`
    FOREIGN KEY (`Proizvodjac_idProizvodjac`)
    REFERENCES `mservis`.`proizvodjac` (`idProizvodjac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`izvjestaj` (
  `idIzvjestaj` INT(11) NOT NULL,
  `Opis` VARCHAR(255) NULL DEFAULT NULL,
  `DatumIzdavanja` DATE NOT NULL,
  `Putanja` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idIzvjestaj`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`artikal_has_izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`artikal_has_izvjestaj` (
  `Artikal_idArtikal` INT(11) NOT NULL,
  `Izvjestaj_idIzvjestaj` INT(11) NOT NULL,
  `Kolicina` INT(11) NOT NULL,
  PRIMARY KEY (`Artikal_idArtikal`, `Izvjestaj_idIzvjestaj`),
  INDEX `fk_Artikal_has_Izvjestaj_Izvjestaj1_idx` (`Izvjestaj_idIzvjestaj` ASC) VISIBLE,
  INDEX `fk_Artikal_has_Izvjestaj_Artikal1_idx` (`Artikal_idArtikal` ASC) VISIBLE,
  CONSTRAINT `fk_Artikal_has_Izvjestaj_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `mservis`.`artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Artikal_has_Izvjestaj_Izvjestaj1`
    FOREIGN KEY (`Izvjestaj_idIzvjestaj`)
    REFERENCES `mservis`.`izvjestaj` (`idIzvjestaj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`dodatnaoprema`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`dodatnaoprema` (
  `Tip` VARCHAR(255) NULL DEFAULT NULL,
  `idDodatnaOprema` INT(11) NOT NULL,
  PRIMARY KEY (`idDodatnaOprema`),
  INDEX `fk_DodatnaOprema_Artikal1_idx` (`idDodatnaOprema` ASC) VISIBLE,
  CONSTRAINT `fk_DodatnaOprema_Artikal1`
    FOREIGN KEY (`idDodatnaOprema`)
    REFERENCES `mservis`.`artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`klijent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`klijent` (
  `idOsoba_Klijent` INT(11) NOT NULL,
  PRIMARY KEY (`idOsoba_Klijent`),
  CONSTRAINT `fk_Klijent_Osoba1`
    FOREIGN KEY (`idOsoba_Klijent`)
    REFERENCES `mservis`.`osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`potvrdaoprijemu`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`potvrdaoprijemu` (
  `idPotvrdaOPrijemu` INT(11) NOT NULL,
  `Klijent_idOsoba_Klijent` INT(11) NOT NULL,
  PRIMARY KEY (`idPotvrdaOPrijemu`, `Klijent_idOsoba_Klijent`),
  INDEX `fk_PotvrdaOPrijemu_Klijent1_idx` (`Klijent_idOsoba_Klijent` ASC) VISIBLE,
  CONSTRAINT `fk_PotvrdaOPrijemu_Klijent1`
    FOREIGN KEY (`Klijent_idOsoba_Klijent`)
    REFERENCES `mservis`.`klijent` (`idOsoba_Klijent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`telefon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`telefon` (
  `Model` VARCHAR(255) NULL DEFAULT NULL,
  `Specifikacije` VARCHAR(255) NULL DEFAULT NULL,
  `Slika` BLOB NULL DEFAULT NULL,
  `DatumProizvodnje` DATE NULL DEFAULT NULL,
  `idTelefon` INT(11) NOT NULL,
  `PotvrdaOPrijemu_idPotvrdaOPrijemu` INT(11) NULL DEFAULT NULL,
  `PotvrdaOPrijemu_Klijent_idOsoba_Klijent` INT(11) NULL DEFAULT NULL,
  `Boja` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idTelefon`),
  INDEX `fk_Telefon_Artikal1_idx` (`idTelefon` ASC) VISIBLE,
  INDEX `fk_Telefon_PotvrdaOPrijemu1_idx` (`PotvrdaOPrijemu_idPotvrdaOPrijemu` ASC, `PotvrdaOPrijemu_Klijent_idOsoba_Klijent` ASC) VISIBLE,
  CONSTRAINT `fk_Telefon_Artikal1`
    FOREIGN KEY (`idTelefon`)
    REFERENCES `mservis`.`artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Telefon_PotvrdaOPrijemu1`
    FOREIGN KEY (`PotvrdaOPrijemu_idPotvrdaOPrijemu` , `PotvrdaOPrijemu_Klijent_idOsoba_Klijent`)
    REFERENCES `mservis`.`potvrdaoprijemu` (`idPotvrdaOPrijemu` , `Klijent_idOsoba_Klijent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`zaposleni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`zaposleni` (
  `JMB` INT(11) NOT NULL,
  `RadnoMjesto` VARCHAR(255) NOT NULL,
  `Adresa` VARCHAR(255) NULL DEFAULT NULL,
  `idOsoba_Zaposleni` INT(11) NOT NULL,
  PRIMARY KEY (`idOsoba_Zaposleni`),
  CONSTRAINT `fk_Zaposleni_Osoba`
    FOREIGN KEY (`idOsoba_Zaposleni`)
    REFERENCES `mservis`.`osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`servistelefona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`servistelefona` (
  `idServisa` INT(11) NOT NULL,
  `OpisKvara` VARCHAR(255) NULL DEFAULT NULL,
  `StanjeTelefona` VARCHAR(255) NULL DEFAULT NULL,
  `DatumPrijema` DATETIME NOT NULL,
  `DatumPreuzimanja` DATETIME NULL DEFAULT NULL,
  `Telefon_idTelefon` INT(11) NOT NULL,
  `Zaposleni_idOsoba_Zaposleni` INT(11) NOT NULL,
  PRIMARY KEY (`idServisa`),
  INDEX `fk_ServisTelefona_Telefon1_idx` (`Telefon_idTelefon` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_Zaposleni1_idx` (`Zaposleni_idOsoba_Zaposleni` ASC) VISIBLE,
  CONSTRAINT `fk_ServisTelefona_Telefon1`
    FOREIGN KEY (`Telefon_idTelefon`)
    REFERENCES `mservis`.`telefon` (`idTelefon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_Zaposleni1`
    FOREIGN KEY (`Zaposleni_idOsoba_Zaposleni`)
    REFERENCES `mservis`.`zaposleni` (`idOsoba_Zaposleni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`racun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`racun` (
  `idRacun` INT(11) NOT NULL,
  `Vrijeme` DATETIME NULL DEFAULT NULL,
  `UkupnaCijena` INT(11) NULL DEFAULT NULL,
  `idIzdavaca` INT(11) NOT NULL,
  `ServisTelefona_idServisa` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idRacun`),
  INDEX `fk_Racun_Zaposleni1_idx` (`idIzdavaca` ASC) VISIBLE,
  INDEX `fk_Racun_ServisTelefona1_idx` (`ServisTelefona_idServisa` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_ServisTelefona1`
    FOREIGN KEY (`ServisTelefona_idServisa`)
    REFERENCES `mservis`.`servistelefona` (`idServisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Racun_Zaposleni1`
    FOREIGN KEY (`idIzdavaca`)
    REFERENCES `mservis`.`zaposleni` (`idOsoba_Zaposleni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`racun_has_artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`racun_has_artikal` (
  `Racun_idRacun` INT(11) NOT NULL,
  `Artikal_idArtikal` INT(11) NOT NULL,
  `Kolicina` INT(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Racun_idRacun`, `Artikal_idArtikal`),
  INDEX `fk_Racun_has_Artikal_Artikal1_idx` (`Artikal_idArtikal` ASC) VISIBLE,
  INDEX `fk_Racun_has_Artikal_Racun1_idx` (`Racun_idRacun` ASC) VISIBLE,
  CONSTRAINT `fk_Racun_has_Artikal_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `mservis`.`artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Racun_has_Artikal_Racun1`
    FOREIGN KEY (`Racun_idRacun`)
    REFERENCES `mservis`.`racun` (`idRacun`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`rezervnidio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`rezervnidio` (
  `Opis` VARCHAR(255) NULL DEFAULT NULL,
  `idRezervniDio` INT(11) NOT NULL,
  `Telefon_idTelefon` INT(11) NOT NULL,
  `idTelefon_RezervniDio` INT(11) NOT NULL,
  PRIMARY KEY (`idRezervniDio`, `Telefon_idTelefon`),
  INDEX `fk_RezervniDio_Artikal1_idx` (`idRezervniDio` ASC) VISIBLE,
  INDEX `fk_RezervniDio_Telefon1_idx` (`Telefon_idTelefon` ASC, `idTelefon_RezervniDio` ASC) VISIBLE,
  INDEX `fk_RezervniDio_Telefon1` (`idTelefon_RezervniDio` ASC) VISIBLE,
  CONSTRAINT `fk_RezervniDio_Artikal1`
    FOREIGN KEY (`idRezervniDio`)
    REFERENCES `mservis`.`artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_RezervniDio_Telefon1`
    FOREIGN KEY (`idTelefon_RezervniDio`)
    REFERENCES `mservis`.`telefon` (`idTelefon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`servistelefona_has_izvjestaj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`servistelefona_has_izvjestaj` (
  `ServisTelefona_idServisa` INT(11) NOT NULL,
  `Izvjestaj_idIzvjestaj` INT(11) NOT NULL,
  PRIMARY KEY (`ServisTelefona_idServisa`, `Izvjestaj_idIzvjestaj`),
  INDEX `fk_ServisTelefona_has_Izvjestaj_Izvjestaj1_idx` (`Izvjestaj_idIzvjestaj` ASC) VISIBLE,
  INDEX `fk_ServisTelefona_has_Izvjestaj_ServisTelefona1_idx` (`ServisTelefona_idServisa` ASC) VISIBLE,
  CONSTRAINT `fk_ServisTelefona_has_Izvjestaj_Izvjestaj1`
    FOREIGN KEY (`Izvjestaj_idIzvjestaj`)
    REFERENCES `mservis`.`izvjestaj` (`idIzvjestaj`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ServisTelefona_has_Izvjestaj_ServisTelefona1`
    FOREIGN KEY (`ServisTelefona_idServisa`)
    REFERENCES `mservis`.`servistelefona` (`idServisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`telefon_osoba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`telefon_osoba` (
  `BrojTelefona` INT(11) NOT NULL,
  `Osoba_idOsoba` INT(11) NOT NULL,
  PRIMARY KEY (`Osoba_idOsoba`, `BrojTelefona`),
  INDEX `fk_Telefon_osoba_Osoba1_idx` (`Osoba_idOsoba` ASC) VISIBLE,
  CONSTRAINT `fk_Telefon_osoba_Osoba1`
    FOREIGN KEY (`Osoba_idOsoba`)
    REFERENCES `mservis`.`osoba` (`idOsoba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`ugradjenirezervnidio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`ugradjenirezervnidio` (
  `RezervniDio_idRezervniDio` INT(11) NOT NULL,
  `RezervniDio_Telefon_idTelefon` INT(11) NOT NULL,
  `ServisTelefona_idServisa` INT(11) NOT NULL,
  PRIMARY KEY (`RezervniDio_idRezervniDio`, `RezervniDio_Telefon_idTelefon`, `ServisTelefona_idServisa`),
  INDEX `fk_UgradjeniRezervniDio_RezervniDio1_idx` (`RezervniDio_idRezervniDio` ASC, `RezervniDio_Telefon_idTelefon` ASC) VISIBLE,
  INDEX `fk_UgradjeniRezervniDio_ServisTelefona1_idx` (`ServisTelefona_idServisa` ASC) VISIBLE,
  CONSTRAINT `fk_UgradjeniRezervniDio_RezervniDio1`
    FOREIGN KEY (`RezervniDio_idRezervniDio` , `RezervniDio_Telefon_idTelefon`)
    REFERENCES `mservis`.`rezervnidio` (`idRezervniDio` , `Telefon_idTelefon`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_UgradjeniRezervniDio_ServisTelefona1`
    FOREIGN KEY (`ServisTelefona_idServisa`)
    REFERENCES `mservis`.`servistelefona` (`idServisa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mservis`.`Dobavljac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`Dobavljac` (
  `idDobavljac` INT NOT NULL,
  `Naziv` VARCHAR(255) NOT NULL,
  `Adresa` VARCHAR(255) NULL,
  `Telefon` VARCHAR(255) NULL,
  `E-mail` VARCHAR(255) NULL,
  `Fax` VARCHAR(255) NULL,
  PRIMARY KEY (`idDobavljac`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`Artikal_has_Dobavljac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`Artikal_has_Dobavljac` (
  `Dobavljac_idDobavljac` INT NOT NULL,
  `Artikal_idArtikal` INT NOT NULL,
  PRIMARY KEY (`Dobavljac_idDobavljac`, `Artikal_idArtikal`),
  INDEX `fk_Dobavljac_has_Artikal_Artikal1_idx` (`Artikal_idArtikal` ASC) VISIBLE,
  INDEX `fk_Dobavljac_has_Artikal_Dobavljac1_idx` (`Dobavljac_idDobavljac` ASC) VISIBLE,
  CONSTRAINT `fk_Dobavljac_has_Artikal_Dobavljac1`
    FOREIGN KEY (`Dobavljac_idDobavljac`)
    REFERENCES `mservis`.`Dobavljac` (`idDobavljac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dobavljac_has_Artikal_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`Narudzba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`Narudzba` (
  `idNarudzba` INT NOT NULL,
  `DatumNarudzbe` DATETIME NOT NULL,
  `Dobavljac_idDobavljac` INT NOT NULL,
  PRIMARY KEY (`idNarudzba`),
  INDEX `fk_Narudzba_Dobavljac1_idx` (`Dobavljac_idDobavljac` ASC) VISIBLE,
  CONSTRAINT `fk_Narudzba_Dobavljac1`
    FOREIGN KEY (`Dobavljac_idDobavljac`)
    REFERENCES `mservis`.`Dobavljac` (`idDobavljac`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`Narudzba_has_Artikal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`Narudzba_has_Artikal` (
  `Narudzba_idNarudzba` INT NOT NULL,
  `Artikal_idArtikal` INT NOT NULL,
  `Kolicina` INT NOT NULL,
  PRIMARY KEY (`Narudzba_idNarudzba`, `Artikal_idArtikal`),
  INDEX `fk_Narudzba_has_Artikal_Artikal1_idx` (`Artikal_idArtikal` ASC) VISIBLE,
  INDEX `fk_Narudzba_has_Artikal_Narudzba1_idx` (`Narudzba_idNarudzba` ASC) VISIBLE,
  CONSTRAINT `fk_Narudzba_has_Artikal_Narudzba1`
    FOREIGN KEY (`Narudzba_idNarudzba`)
    REFERENCES `mservis`.`Narudzba` (`idNarudzba`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Narudzba_has_Artikal_Artikal1`
    FOREIGN KEY (`Artikal_idArtikal`)
    REFERENCES `mydb`.`Artikal` (`idArtikal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mservis`.`ModelTelefona_has_RezervniDio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mservis`.`ModelTelefona_has_RezervniDio` (
  `ModelTelefona_idModelTelefona` INT NOT NULL,
  `RezervniDio_idRezervniDio` INT NOT NULL,
  PRIMARY KEY (`ModelTelefona_idModelTelefona`, `RezervniDio_idRezervniDio`),
  INDEX `fk_ModelTelefona_has_RezervniDio_RezervniDio1_idx` (`RezervniDio_idRezervniDio` ASC) VISIBLE,
  INDEX `fk_ModelTelefona_has_RezervniDio_ModelTelefona1_idx` (`ModelTelefona_idModelTelefona` ASC) VISIBLE,
  CONSTRAINT `fk_ModelTelefona_has_RezervniDio_ModelTelefona1`
    FOREIGN KEY (`ModelTelefona_idModelTelefona`)
    REFERENCES `mservis`.`ModelTelefona` (`idModelTelefona`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ModelTelefona_has_RezervniDio_RezervniDio1`
    FOREIGN KEY (`RezervniDio_idRezervniDio`)
    REFERENCES `mydb`.`RezervniDio` (`idRezervniDio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
