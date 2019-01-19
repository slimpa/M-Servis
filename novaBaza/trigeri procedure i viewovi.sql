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