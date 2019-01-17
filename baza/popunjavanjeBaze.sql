INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Samsung');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Nokia');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Alcatel');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Apple');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Huawei');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Xiaomi');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('HTC');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Sony');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('LG');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('ZTE');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Motorola');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Lenovo');
INSERT INTO `m:servis`.`proizvodjac` (`Naziv`) VALUES ('Microsoft');


INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('Mobis', 'Vuka Karadžića 23 Banja Luka', '051 333 111', 'mobis@mobile.com', '0');
INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('Beoecotle', 'Omladinska ulica bb Banja Luka', '051 241 222', 'beo@mail.com', '0');
INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('MD Electronics', 'Aleja Svetog Save 1', '065 222 333 ', 'md@electronics.org', '0');

INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Maska');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Punjač');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Slušalice');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Kabal');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('USB');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Powerbank');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Ostalo');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Zvučnik');
INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`) VALUES ('Folija');

INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`) VALUES ('Pokvaren');
INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`) VALUES ('Na servisu');
INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`) VALUES ('Popravljen');
INSERT INTO `m:servis`.`stanjetelefona` (`Stanje`) VALUES ('Nemoguće popraviti');

INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Zamjena ekrana', '50');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Dekodiranje telefona', '20');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Instalacija softvera', '30');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Zamjena zvučnika', '44.5');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Zamjena baterija', '10.5');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Reinstalacija OS-a', '30');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Popravak tipki', '20.5');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`) VALUES ('Zamjena stakla na ekranu', '10.99');


INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Samsung S9+', '1', '111000', '5', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Noika 3310', '2', '999999', '3', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Maska 14', '1', '333333', '20', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Slusalice Sony', '8', '333213', '10', '0');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Displej za HTC Desire 5', '7', '000000', '3', '0');





#ovde prvo insertujem u osobu pa onda na osnovu dobijenog autoincrement ID unesem u admin i zaposleni, tako da ne mora da radi i kod vas,morate da 
# vidite koji id je uzet
INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `KorisnickoIme`, `Lozinka`, `BrojTelefona`, `Obrisano`) VALUES ('Test', 'Test', 'student', 'student', '111 111', '0');
INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `KorisnickoIme`, `Lozinka`, `BrojTelefona`, `Obrisano`) VALUES ('Test2', 'Test2', 'admin', 'admin', '222 222', '0');
INSERT INTO `m:servis`.`zaposleni` (`IdZaposleni`, `RadnoMjesto`) VALUES ('1', 'Serviser/Trgovac');
INSERT INTO `m:servis`.`admin` (`IdAdmin`, `NazivFirme`) VALUES ('2', 'ETF BL');

