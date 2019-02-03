create view svi_zaposleni as
select osoba.IdOsoba, osoba.Ime, osoba.Prezime, zaposleni.KorisnickoIme, zaposleni.RadnoMjesto, osoba.BrojTelefona
from osoba inner join zaposleni where osoba.IdOsoba=zaposleni.IdZaposleni and osoba.Obrisano=0;

create view svi_admini as
select osoba.IdOsoba, Ime, Prezime, KorisnickoIme, BrojTelefona, NazivFirme, Lozinka
 from `m:servis`.`osoba` inner join `m:servis`.`admin` 
 on osoba.IdOsoba=`m:servis`.`admin`.IdAdmin where osoba.Obrisano=0;
 
 
 delimiter $$
create procedure dodaj_admina(in pIme varchar(255), in pPrezime varchar(255), in pKorisnickoIme varchar(255),
in pLozinka varchar(600), in pBrojTelefona varchar(255), in pFirma varchar(255))
	begin
		declare noviId int;
		
		INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `BrojTelefona`, `Obrisano`)
        VALUES (pIme, pPrezime, pBrojTelefona, '0');
        
        SELECT LAST_INSERT_ID() into noviId;
        
        INSERT INTO `m:servis`.`admin` (`IdAdmin`, `NazivFirme`, `KorisnickoIme`, `Lozinka`)
        VALUES (noviId, pFirma, pKorisnickoIme, pLozinka);


	end$$
delimiter ;


delimiter $$
create procedure dodaj_zaposlenog(in pIme varchar(255), in pPrezime varchar(255), in pKorisnickoIme varchar(255),
in pLozinka varchar(600), in pBrojTelefona varchar(255), in pRadnoMjesto varchar(255))
	begin
		declare noviId int;
		
		INSERT INTO `m:servis`.`osoba` (`Ime`, `Prezime`, `BrojTelefona`, `Obrisano`)
        VALUES (pIme, pPrezime, pBrojTelefona, '0');
        
        SELECT LAST_INSERT_ID() into noviId;
        
        INSERT INTO `m:servis`.`zaposleni` (`IdZaposleni`, `RadnoMjesto`, `KorisnickoIme`, `Lozinka`)
        VALUES (noviId, pRadnoMjesto, pKorisnickoIme, pLozinka);


	end$$
delimiter ;

delimiter $$
create procedure izmijeni_admina(in pIme varchar(255), in pPrezime varchar(255), 
in pBrojTelefona varchar(255), in pFirma varchar(255), in pIdAdmin int)
	begin
	update osoba set Ime = pIme, Prezime = pPrezime, BrojTelefona = pBrojTelefona where IdOsoba = pIdAdmin;
    update `m:servis`.`admin` set NazivFirme = pFirma where IdAdmin = pIdAdmin;
    end$$
delimiter ;

delimiter $$
create procedure izmijeni_zaposlenog(in pIme varchar(255), in pPrezime varchar(255), 
in pBrojTelefona varchar(255), in pRadnoMjesto varchar(255), in pIdZaposleni int)
	begin
	update osoba set Ime = pIme, Prezime = pPrezime, BrojTelefona = pBrojTelefona where IdOsoba = pIdZaposleni;
    update zaposleni set RadnoMjesto = pRadnoMjesto where IdZaposleni = pIdZaposleni;
    end$$
delimiter ;

delimiter $$
create procedure dodaj_klijenta(in pIme varchar(255), in pPrezime varchar(255), in pBrojTelefona varchar(255))
	begin
    insert into osoba(Ime, Prezime, BrojTelefona, Obrisano) values (pIme, pPrezime, pBrojTelefona, 0);
    insert into klijent(IdKlijent) values(last_insert_id());
	end$$
delimiter ;

create view svi_klijenti as select * from osoba inner join klijent on osoba.IdOsoba = klijent.IdKlijent where Obrisano = 0;

delimiter $$
create procedure dodaj_servis(in pOpisKvara varchar(255), in pSerijskiBrojTelefona varchar(255), in pIdStanjeTelefona int,
in pIdZaposleni int, in pIdModelTelefona int)
	begin
    declare vIdKlijent int;
	select max(IdKlijent) into vIdKlijent from klijent;
    
    insert into servistelefona(IdKlijent, OpisKvara, DatumPrijema, SerijskiBrojTelefona, IdStanjeTelefona, IdZaposleni, IdModelTelefona, TelefonPreuzet)
    values (vIdKlijent, pOpisKvara, now(), pSerijskiBrojTelefona, pIdStanjeTelefona, pIdZaposleni, pIdModelTelefona, 0);
    end$$
delimiter ;


create view rezervni_dijelovi as
select IdRezervniDio, artikal.Naziv,rezervnidio.IdModelTelefona, Opis, artikal.Kolicina, cijena. cijena from rezervnidio natural join artikal natural join modeltelefona natural join cijena 
where rezervnidio.IdRezervniDio=artikal.IdArtikal  and rezervnidio.IdRezervniDio=cijena.IdArtikla;



create view svi_telefoni as
select modeltelefona.IdModelTelefona, artikal.Naziv, modeltelefona.NazivModela, proizvodjac.Naziv as Proizvodjac,telefon.Boja, modeltelefona.Specifikacija, telefon.SerijskiBroj, cijena.Cijena 
from telefon natural join modeltelefona natural join artikal natural join cijena inner join proizvodjac
where modeltelefona.IdModelTelefona=telefon.IdModeTelefona  and modeltelefona.IdModelTelefona=artikal.IdArtikal and modeltelefona.IdModelTelefona=cijena.IdArtikla and artikal.IdProizvodjac=proizvodjac.IdProizvodjac;



create view dodatna_oprema as
select dodatnaoprema.IdDodatnaOprema, artikal.Naziv, tipdodatneopreme.TipOpreme, dodatnaoprema.Boja, modeltelefona.NazivModela, artikal.Kolicina, cijena.Cijena
from dodatnaoprema natural join tipdodatneopreme natural join artikal natural join cijena natural join modeltelefona
where dodatnaoprema.IdDodatnaOprema=artikal.IdArtikal and dodatnaoprema.IdTipDodatneOpreme= tipdodatneopreme.IdTipDodatneOpreme and artikal.IdArtikal=cijena.IdArtikla and modeltelefona.IdModelTelefona=dodatnaoprema.IdModelTelefona;


Alter table narudzba_has_artikal add column Kolicina int;

create view dnevni_izvjestaj as
select racun.IdRacun,date(racun.Vrijeme) as Datum, artikal.IdArtikal,artikal.Naziv, racun_has_artikal.Kolicina, cijena.Cijena
from cijena inner join artikal  inner join racun_has_artikal inner join racun
on cijena.IdArtikla=artikal.IdArtikal and artikal.IdArtikal=racun_has_artikal.IdArtikal and racun.IdRacun=racun_has_artikal.IdRacun
where ((cijena.DatumDo is not null and cijena.DatumOd<racun.Vrijeme and cijena.DatumDo>racun.Vrijeme )
 or (cijena.DatumOd<racun.Vrijeme and cijena.TrenutnaCijena=1)) and (date(racun.Vrijeme)=CURDATE())
 order by Naziv;
 
ALTER TABLE artikal AUTO_INCREMENT = 5000;

create view rezervni_dio_naziv as
select rezervnidio.IdRezervniDio, artikal.Naziv, rezervnidio.IdModelTelefona from rezervnidio inner join artikal on rezervnidio.IdRezervniDio = artikal.IdArtikal;
