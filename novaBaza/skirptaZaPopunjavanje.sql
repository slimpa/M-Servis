INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Samsung','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Nokia','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Alcatel','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Apple','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Huawei','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Xiaomi','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('HTC','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Sony','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('LG','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('ZTE','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Motorola','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Lenovo','0');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`,`Obrisano`) VALUES ('Microsoft','0');


INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('Mobis', 'Vuka Karadžića 23 Banja Luka', '051 333 111', 'mobis@mobile.com', '0');
INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('Beoecotle', 'Omladinska ulica bb Banja Luka', '051 241 222', 'beo@mail.com', '0');
INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('MD Electronics', 'Aleja Svetog Save 1', '065 222 333 ', 'md@electronics.org', '0');

INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Maska','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Punjač','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Slušalice','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Kabal','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('USB','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Powerbank','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Ostalo','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Zvučnik','0');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES ('Folija','0');

INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`,`Obrisano`) VALUES ('Pokvaren','0');
INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`,`Obrisano`) VALUES ('Na servisu','0');
INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`,`Obrisano`) VALUES ('Popravljen','0');
INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`,`Obrisano`) VALUES ('Nemoguće popraviti','0');

INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena ekrana', '50','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Dekodiranje telefona', '20','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Instalacija softvera', '30','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena zvučnika', '44.5','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena baterija', '10.5','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Reinstalacija OS-a', '30','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Popravak tipki', '20.5','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena stakla na ekranu', '10.99','0');


INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Samsung S9+', '1', '111000', '5', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Noika 3310', '2', '999999', '3', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Maska 14', '1', '333333', '20', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Slusalice Sony', '8', '333213', '10', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Displej za HTC Desire 5', '7', '000000', '3', '0');


INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `BrojTelefona`, `Obrisano`) VALUES ('AdminIme', 'AdminPrezime', '111 111', '0');
INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `BrojTelefona`, `Obrisano`) VALUES ('ZaposleniIme', 'ZaposleniPrezime', '222 222', '0');
INSERT INTO `m:servis`.`admin` (`IdAdmin`, `NazivFirme`, `KorisnickoIme`, `Lozinka`) VALUES ('1', 'ETF BL', 'admin', 'admin');
INSERT INTO `m:servis`.`zaposleni` (`IdZaposleni`, `RadnoMjesto`, `KorisnickoIme`, `Lozinka`) VALUES ('2', 'Trgovac/Serviser', 'student', 'student');







