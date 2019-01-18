create view svi_zaposleni as
select osoba.IdOsoba, osoba.Ime, osoba.Prezime, zaposleni.KorisnickoIme, zaposleni.RadnoMjesto
from osoba inner join zaposleni where osoba.IdOsoba=zaposleni.IdZaposleni and osoba.Obrisano=0;

create view svi_admini as
select osoba.IdOsoba, Ime, Prezime, KorisnickoIme, BrojTelefona, NazivFirme
 from `m:servis`.`osoba` inner join `m:servis`.`admin` 
 on osoba.IdOsoba=`m:servis`.`admin`.IdAdmin where osoba.Obrisano=0;