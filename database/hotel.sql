# potrei fare un attributo derivabile su personale hotel che mi dice se e' personale delle pulizie oppure personale manutenzione

# devo mettere Tipo camera in Camera e toglierlo da prenotazione che poi ci posso fare l'except da la se faccio in questo modo
Drop database if exists Hotel_Progetto;
create database Hotel_Progetto;
use Hotel_Progetto;

CREATE TABLE Cliente(
                        cf        varchar(20)       not null,
                        nome    varchar(20)       not null,
                        cognome varchar(20)        not null,
                        email   varchar(200)      not null,

                        via     varchar(200)      not null,
                        civico  varchar(200)      not null,
                        citta   varchar(200)      not null,
                        dataNascita DATE          not null,
                        eta         INT             AS (2024 - YEAR(dataNascita)),

                        primary key(cf)
);

#ALTER TABLE Cliente
#ADD COLUMN eta INT AS (YEAR(NOW()) - YEAR(dataNascita) - (RIGHT(NOW(), 5) < RIGHT(dataNascita, 5))) STORED;

CREATE TABLE Camera(
    numeroStanza	int			not null,
    postiLetto		int			not null,
    prezzo			double		not null,
	tipoCamera		varchar(20) not null,

    primary key(numeroStanza)

);

CREATE TABLE Prenotazione(
                             cameranumeroStanza int     not null,

                             codice       varchar(20) not null,
                             dataArrivo     date      not null,
                             dataPartenza    date      not null,

                             primary key(codice),

                             foreign key (cameranumeroStanza) REFERENCES Camera(numeroStanza)
);

-- New Junction Table to handle the Many-to-Many relationship
CREATE TABLE Cliente_Prenotazione(
                                     cliente_cf             varchar(20) not null,
                                     prenotazione_codice    varchar(20) not null,

                                     primary key(cliente_cf, prenotazione_codice),

                                     foreign key (cliente_cf) REFERENCES Cliente(cf),
                                     foreign key (prenotazione_codice) REFERENCES Prenotazione(codice)
);

								
CREATE TABLE PersonaleHotel (	
	cf 				varchar(20)	not null,
	nome			varchar(20)	not null,
    cognome			varchar(20)	not null,
    

    
    primary key(cf)
    
);


CREATE TABLE PersonalePulizie (
	cf				varchar(20)	,
    primary key (cf),
    foreign key (cf) REFERENCES PersonaleHotel(cf)
);

 CREATE TABLE PersonaleManutenzione(
 	cf				varchar(20)	,
#	descrizione		varchar(20)	,


	primary key (cf),
    foreign key (cf) REFERENCES PersonaleHotel(cf)
 );
 

 
 CREATE TABLE Competenze (
	descrizione		varchar(20),	
    cf				varchar(20),
    
#    primary key(cf)			   ,
    
    foreign key (cf) REFERENCES PersonaleManutenzione(cf)
 
 );
 

CREATE TABLE Segnalazione (
    codice 			varchar(20)	not null,
	segnalazione	varchar(100) not null,
    
    cameranumeroStanza int		not null,
    cf				varchar(20)	not null,
    
    primary key (codice)					,
    foreign key (cf) REFERENCES PersonalePulizie(cf),
    foreign key (cameranumeroStanza) REFERENCES Camera(numeroStanza)
    
);


CREATE TABLE Lavora (
	hotelcf				varchar(20)	not null,
	cameraNumeroStanza	int			not null,
	oraLavoro			time		not null,
	
	primary key(hotelcf,cameraNumeroStanza),
    
    foreign key (hotelcf) 							REFERENCES PersonaleHotel(cf),
    foreign key (cameraNumeroStanza) 				REFERENCES Camera(numeroStanza)
    
);

-- Inserimento di alcuni clienti
INSERT INTO Cliente (cf, nome, cognome, email, via, civico, citta, dataNascita)
VALUES 
('CF123456', 'Mario', 'Rossi', 'mario.rossi@email.com', 'Via Roma', '123', 'Roma','1990-05-15'),
('CF789012', 'Giulia', 'Bianchi', 'giulia.bianchi@email.com', 'Via Milano', '456', 'Milano','1988-10-20'),
('CF345678', 'Luca', 'Verdi', 'luca.verdi@email.com', 'Via Napoli', '789', 'Napoli','1985-03-08'),
('CF987654', 'Francesca', 'Esposito', 'francesca.esposito@email.com', 'Via Palermo', '321', 'Napoli','1992-07-23');
;

-- Inserimento delle camere
INSERT INTO Camera (numeroStanza, postiLetto, prezzo,tipoCamera )
VALUES 
(101, 2, 100.00, 'singola'),
(102, 1, 80.00, 'doppia'),
(103, 3, 150.00, 'singola');

-- Inserimento di prenotazioni
INSERT INTO Prenotazione (cliente_cf, cameranumeroStanza, codice, dataArrivo, dataPartenza)
VALUES 

('CF123456', 101, 'PREN001', '2024-03-01', '2024-03-05'),
('CF987654', 101, 'PREN002', '2024-03-01', '2024-03-05'),


('CF789012', 102, 'PREN003', '2024-03-10', '2024-03-15'),
('CF345678', 103, 'PREN004', '2024-03-20', '2024-03-25');

-- Inserimento del personale dell'hotel
INSERT INTO PersonaleHotel (cf, nome, cognome)
VALUES 
('CF112233', 'Alessandro', 'Rossi'), # PULIZIE
('CF445566', 'Giorgia', 'Bianchi'),	# MANUTENZIONE - IDRAULICO
('CF778899', 'Matteo', 'Verdi'),
('CF998877', 'Giovanni', 'Verdi')


;	# PULIZIE

-- Inserimento del personale delle pulizie
INSERT INTO PersonalePulizie (cf)
VALUES 
('CF112233'),
('CF778899')
;


-- Inserimento del personale di manutenzione
INSERT INTO PersonaleManutenzione (cf)
VALUES 
('CF445566'),
('CF998877')
;

-- Inserimento delle competenze del personale di manutenzione
INSERT INTO Competenze (descrizione, cf)				
VALUES 
('Idraulico',    'CF445566'),
('Elettricista', 'CF445566'),

('Idraulico',    'CF998877')

;

-- Inserimento di segnalazioni
INSERT INTO Segnalazione (codice, segnalazione, cameranumeroStanza, cf)
VALUES 
('SEG001', 'Luce non funzionante', 101, 'CF112233'),
('SEG002', 'Aria condizionata non funzionante', 103, 'CF778899')
;
 

-- Inserimento dell'orario di lavoro
INSERT INTO Lavora (hotelcf, cameraNumeroStanza, oraLavoro)
VALUES 
('CF112233', 101, '08:00:00'),
('CF112233', 102, '09:00:00'),
('CF112233', 103, '010:00:00'),

('CF445566', 102, '09:00:00');


# (1) Seleziona tutti i clienti che sono di Roma o di Milano (nome, cognome, citta)
select nome, cognome, citta, eta from cliente
where citta = 'Roma' or citta = 'Milano'
;


# (2) Seleziona tutti i clienti in ordine alfabetico per nome (nome, cf)
Select nome, cf from cliente
order by nome Asc
;


# (3) Seleziona i clienti che hanno prenotato camere con 2 posti letto (cf)
Select cliente_cf from Prenotazione
join Camera on Prenotazione.cameranumeroStanza = Camera.numeroStanza
where Camera.postiLetto = 2
;

# (4) seleziona le stanze che sono state prenotate da piu' di un cliente (numeroStanza, numeroStanze)
create view contaPrenotazioni as
Select cameranumeroStanza, count(cameranumeroStanza) as numeroStanze from Prenotazione
group by cameranumeroStanza
;

select * from contaPrenotazioni
where contaPrenotazioni.numeroStanze > 1
;

# (5) Seleziona la camera che e' stata prenotata da piu' clienti

select * from contaPrenotazioni
where contaPrenotazioni.numeroStanze = (Select max(numeroStanze) from contaPrenotazioni)
;

#(6) seleziona il codice di segnalazione e il cf del personale e la descrizione della segnalazione della camera che e' stata prenotata da piu' clienti

select codice, cf,  segnalazione from Segnalazione
join contaPrenotazioni on Segnalazione.cameranumeroStanza = contaPrenotazioni.cameranumeroStanza

where contaPrenotazioni.numeroStanze = (Select max(numeroStanze) from contaPrenotazioni)
;


#(7) seleziona i personali hotel che hanno idraulico come competenza (descrizione, cf, nome, cognome)

Select Competenze.descrizione, PersonaleHotel.cf , PersonaleHotel.nome, PersonaleHotel.cognome from PersonaleHotel
join Competenze  on PersonaleHotel.cf = Competenze.cf
where Competenze.descrizione = 'Idraulico'
group by PersonaleHotel.cf
;


# (8) seleziona il personale hotel manutenzione che e' idraulico ma non elettricista ( cf )
SELECT Competenze.cf
FROM Competenze
WHERE Competenze.descrizione = 'Idraulico'

EXCEPT

SELECT Competenze.cf
FROM Competenze
WHERE Competenze.descrizione = 'Elettricista'
;


# (9) seleziona il personale di manutenzione dell'hotel che lavora nella camera piu' costosa ( cf, nome, cognome, contaPrezzo, ora )

create view contaPrezzo as
SELECT cf, nome, cognome, oraLavoro, sum(prezzo) as calcolaPrezzo from PersonaleHotel
join Lavora on Lavora.hotelcf = PersonaleHotel.cf
join Camera on Camera.numeroStanza = Lavora.cameraNumeroStanza
group by cf,nome,cognome, oraLavoro
;

Select * from contaPrezzo where
calcolaPrezzo = (Select max(calcolaPrezzo) from contaPrezzo)
;

# (10) seleziona il personale dell'hotel che lavora nelle camere singole (cf, ora)
SELECT hotelcf, oraLavoro from Lavora
join Camera on Camera.numeroStanza = Lavora.cameraNumeroStanza
where tipoCamera = 'singola'
;

# (11) DIVISIONE elencare il Personale dell'hotel che lavora in tutte le camere
Select * From PersonaleHotel
where not exists (
	Select * from Camera
    where not exists (
		Select * from Lavora where
			Lavora.hotelcf = PersonaleHotel.cf and
            Camera.numeroStanza = Lavora.cameraNumeroStanza
            ))
;


