# M-Servis
Projektni iz informacionih sistema
------------------------------------------------------------------------------------------------------------------------------------
Bazu sam modifikovao! Trebate uraditi DROP svoje baze i onda izvrsiti skripte u sledecem redu:
1.Skripta za kreiranje
2.Skripta koja sadrzi trigere,poglede i procedure
3.Skripta za popunjavanje

Sve JAR fajlove u folderu lib treba ukljuciti u projekat!
------------------------------------------------------------------------------------------------------------------------------------

Uputstvo za GIT

Kloniranje repozitorija:
git clone adresaRepozitorijuma
git status - provjera stanja fajlova koje sam dodao ili izmijenio da li su spremni za commit ili ne
Nakon izmjena
git add . je za sve, za odredjene fajlove njihova imena

git commit -m "poruka"
git push origin master

git pull ->da se povece zadnja verzija sa gita
# Baza
Kada iz foldera gdje se nalazi projekat pokrenemo git bash trenutno smo na master grani, za prelazak na granu sa bazom izvrsimo komandu

git checkout baza

Fajlovi u trenutnom folderu ce se promjeniti, tj.prikazace samo fajlove koji se nalaze na grani baza. Nakon izmjena tih fajlove treba uraditi 

git add . je za sve, za odredjene fajlove njihova imena

git commit -m "poruka"

git push origin baza

Za povratak na master granu izvrsiti komandu 

git checkout master




nemojte pusati sve fajlove zbog konflikta
