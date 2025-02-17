create table if not exists public.patient
(
    id        bigint       not null
        primary key,
    dob       date,
    name      varchar(255),
    patientid varchar(255) not null
        unique
);

alter table public.patient
    owner to cs548user;

create index if not exists index_patient_patientid
    on public.patient (patientid);

create table if not exists public.provider
(
    id         bigint       not null
        primary key,
    name       varchar(255),
    npi        varchar(255),
    providerid varchar(255) not null
        unique
);

alter table public.provider
    owner to cs548user;

create table if not exists public.treatment
(
    id          bigint       not null
        primary key,
    dtype       varchar(31),
    diagnosis   varchar(255),
    treatmentid varchar(255) not null
        unique,
    patient_id  bigint
        constraint fk_treatment_patient_id
            references public.patient,
    provider_id bigint
        constraint fk_treatment_provider_id
            references public.provider
);

alter table public.treatment
    owner to cs548user;

create index if not exists index_treatment_treatmentid
    on public.treatment (treatmentid);

create table if not exists public.physiotherapytreatment
(
    id bigint not null
        primary key
        constraint fk_physiotherapytreatment_id
            references public.treatment
);

alter table public.physiotherapytreatment
    owner to cs548user;

create table if not exists public.radiologytreatment
(
    id bigint not null
        primary key
        constraint fk_radiologytreatment_id
            references public.treatment
);

alter table public.radiologytreatment
    owner to cs548user;

create table if not exists public.surgerytreatment
(
    id                    bigint not null
        primary key
        constraint fk_surgerytreatment_id_new
            references public.treatment
        constraint fk_surgerytreatment_id
            references public.treatment,
    dischargeinstructions varchar(255),
    surgerydate           date   not null
        unique
);

alter table public.surgerytreatment
    owner to cs548user;

create table if not exists public.drugtreatment
(
    id        bigint not null
        primary key
        constraint fk_drugtreatment_id
            references public.treatment,
    dosage    double precision,
    drug      varchar(255),
    enddate   date,
    frequency integer,
    startdate date
);

alter table public.drugtreatment
    owner to cs548user;

create index if not exists index_provider_providerid
    on public.provider (providerid);

create table if not exists public.patient_treatment
(
    patient_id    bigint not null
        constraint fk_patient_treatment_patient_id
            references public.patient,
    treatments_id bigint not null
        constraint fk_patient_treatment_treatments_id
            references public.treatment,
    primary key (patient_id, treatments_id)
);

alter table public.patient_treatment
    owner to cs548user;

create table if not exists public.physiotherapytreatment_treatmentdates
(
    physiotherapytreatment_id bigint
        constraint physiotherapytreatment_treatmentdates_physiotherapytreatment_id
            references public.treatment,
    treatmentdates            date
);

alter table public.physiotherapytreatment_treatmentdates
    owner to cs548user;

create table if not exists public.radiologytreatment_treatmentdates
(
    radiologytreatment_id bigint
        constraint fk_radiologytreatment_treatmentdates_radiologytreatment_id
            references public.treatment,
    treatmentdates        date
);

alter table public.radiologytreatment_treatmentdates
    owner to cs548user;

create table if not exists public.provider_treatment
(
    provider_id   bigint not null
        constraint fk_provider_treatment_provider_id
            references public.provider,
    treatments_id bigint not null
        constraint fk_provider_treatment_treatments_id
            references public.treatment,
    primary key (provider_id, treatments_id)
);

alter table public.provider_treatment
    owner to cs548user;

create table if not exists public.treatment_treatment
(
    treatment_id          bigint not null
        constraint fk_treatment_treatment_treatment_id
            references public.treatment,
    followuptreatments_id bigint not null
        constraint fk_treatment_treatment_followuptreatments_id
            references public.treatment,
    primary key (treatment_id, followuptreatments_id)
);

alter table public.treatment_treatment
    owner to cs548user;

create table if not exists public.sequence
(
    seq_name  varchar(50) not null
        primary key,
    seq_count numeric(38)
);

alter table public.sequence
    owner to cs548user;


