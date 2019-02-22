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

INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `BrojTelefona`, `Obrisano`) VALUES ('Admin1', 'Admin1', '111 111', '0');
INSERT INTO `m:servis`.`admin` (`IdAdmin`, `NazivFirme`, `KorisnickoIme`, `Lozinka`) VALUES (LAST_INSERT_ID(), 'ETF BL', 'admin', 'c7ad44cbad762a5da0a452f9e854fdc1e0e7a52a38015f23f3eab1d80b931dd472634dfac71cd34ebc35d16ab7fb8a90c81f975113d6c7538dc69dd8de9077ec');
INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `BrojTelefona`, `Obrisano`) VALUES ('Zaposleni1', 'Zaposleni1', '222 222', '0');
INSERT INTO `m:servis`.`zaposleni` (`IdZaposleni`, `RadnoMjesto`, `KorisnickoIme`, `Lozinka`) VALUES (LAST_INSERT_ID(), 'Trgovac/Serviser', 'student', '32ade5e7c36fa329ea39dbc352743db40da5aa7460ec55f95b999d6371ad20170094d88d9296643f192e9d5433b8d6d817d6777632e556e96e58f741dc5b3550');


INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena ekrana', '50','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Dekodiranje telefona', '20','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Instalacija softvera', '30','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena zvučnika', '44.5','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena baterija', '10.5','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Reinstalacija OS-a', '30','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Popravak tipki', '20.5','0');
INSERT INTO `m:servis`.`cijenovnikusluga` (`Naziv`, `Cijena`,`Obrisano`) VALUES ('Zamjena stakla na ekranu', '10.99','0');


INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('Mobis', 'Vuka Karadžića 23 Banja Luka', '051 333 111', 'mobis@mobile.com', '0');
INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('Beoecotle', 'Omladinska ulica bb Banja Luka', '051 241 222', 'beo@mail.com', '0');
INSERT INTO `m:servis`.`dobavljac` (`Naziv`, `Adresa`, `Telefon`, `Email`, `Obrisano`) VALUES ('MD Electronics', 'Aleja Svetog Save 1', '065 222 333 ', 'md@electronics.org', '0');


INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Samsung J5', '1', '9044145610000', '0', '0');
SET @idModela = LAST_INSERT_ID();
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (@idModela,'299.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`modeltelefona` (`IdModelTelefona`, `NazivModela`, `Specifikacija`) VALUES (@idModela, 'J5 2016', 'specifikacija');
UPDATE `m:servis`.`artikal` SET `Kolicina` = '2' WHERE (`IdArtikal` = @idModela);

#rezervi dio
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Displej Samsung', '1', '9044145610001', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Touchscreen displej', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'70.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Procesor Samsung j5 dual core', '1', '9044145610301', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'CPU', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'39.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('WiFi adapter J5', '1', '9044145610002', '4', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'WiFi adapter Samsung', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'129.90','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Blic prednje kamere', '1', '9044145610033', '3', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Led osvjetljenje', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'49.99','2018-09-08',NULL, '1');
#oprema

#telefon

INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '19210010', 'Siva', '0');
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '40392301', 'Crna', '0');
UPDATE `m:servis`.`artikal` SET `Kolicina` = '2' WHERE (`IdArtikal` = @idModela);

INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Samsung A7 ', '1', '9044145610004', '0', '0');
SET @idModela = LAST_INSERT_ID();
INSERT INTO `m:servis`.`modeltelefona` (`IdModelTelefona`, `NazivModela`, `Specifikacija`) VALUES (@idModela, 'A5 2017', 'specifikacija');
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'499.00','2018-09-08',NULL, '1');
#dijelovi
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Displej Samsung A5', '1', '9044145610005', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Touchscreen displej', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'70.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Procesor Samsung A5 Intelx4', '1', '9044145610006', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'CPU', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'89.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Maticna ploca a523', '1', '9044145610007', '4', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), '', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'139.33','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Prednja kamera 10mpx', '1', '9044145610008', '4', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Osnovna kamera', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'44.00','2018-09-08',NULL, '1');
#oprema
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Samsung Slusalice', '1', '9044145610323', '5', '0');
INSERT INTO `m:servis`.`dodatnaoprema` (`IdDodatnaOprema`, `Boja`, `IdTipDodatneOpreme`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Bijela', '3', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'15.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Samsung Bluetooth slusalice', '1', '9044145610395', '5', '0');
INSERT INTO `m:servis`.`dodatnaoprema` (`IdDodatnaOprema`, `Boja`, `IdTipDodatneOpreme`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Bijela', '3', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'29.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Maska A5', '1', '9044145634005', '5', '0');
INSERT INTO `m:servis`.`dodatnaoprema` (`IdDodatnaOprema`, `Boja`, `IdTipDodatneOpreme`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Crna', '1', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'9.99','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Folija A5 Samsung', '1', '9044145610305', '5', 0);
INSERT INTO `m:servis`.`dodatnaoprema` (`IdDodatnaOprema`, `Boja`, `IdTipDodatneOpreme`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Bijela', '9', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'5.00','2018-09-08',NULL, '1');

#telefoni
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '89014010', 'Plava', '0');
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '89014192', 'Crna', '0');
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '89014343', 'Crna', '0');
UPDATE `m:servis`.`artikal` SET `Kolicina` = '3' WHERE (`IdArtikal` = @idModela);

INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Iphone 7s+', '4', '9044145610009', '0', '0');
SET @idModela = LAST_INSERT_ID();
INSERT INTO `m:servis`.`modeltelefona` (`IdModelTelefona`, `NazivModela`, `Specifikacija`) VALUES (@idModela, 'Iphone 7S Plus', 'specifikacija');
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (@idModela,'499.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Home Return tipke Iphone 7', '4', '9044145610010', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), '', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'49.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Zvucnici komplet 2-1', '4', '3329145610000', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Sistem za ozvucenje', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'55.55','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('NFC adapter', '4', '3329145610001', '4', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), '', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'49.90','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Mikrofon Apple', '4', '3329145610002', '4', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'Iphone mikrofon', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'35.00','2018-09-08',NULL, '1');

#telefoni
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '10010010', 'Bijela', '0');
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '10010011', 'Bijela', '0');
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '10010013', 'Bijela', '0');
INSERT INTO `m:servis`.`telefon` (`IdModeTelefona`, `SerijskiBroj`, `Boja`, `Obrisano`) VALUES (@idModela, '10010015', 'Bijela', '0');
UPDATE `m:servis`.`artikal` SET `Kolicina` = '4' WHERE (`IdArtikal` = @idModela);

INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Telefon Huawei P20', '5', '3329145610003', '0', '0');
SET @idModela = LAST_INSERT_ID();
INSERT INTO `m:servis`.`modeltelefona` (`IdModelTelefona`, `NazivModela`, `Specifikacija`) VALUES (@idModela, 'P20', 'specifikacija');
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'245.90','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Antena Huwavei p20', '5', '3329145610004', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'opis', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'35.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Zvucnici komplet 2-1', '5', '3329145610005', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'opis', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'38.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('NFC adapter', '5', '3329145610306', '5', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'opis', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'44.00','2018-09-08',NULL, '1');
INSERT INTO `m:servis`.`artikal` (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) VALUES ('Kamera zadnja komplet', '5', '3329145610007', '4', '0');
INSERT INTO `m:servis`.`rezervnidio` (`IdRezervniDio`, `Opis`, `IdModelTelefona`) VALUES (LAST_INSERT_ID(), 'opis', @idModela);
INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (LAST_INSERT_ID(),'105.00','2018-09-08',NULL, '1');







