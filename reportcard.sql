PGDMP                 	        z         
   reportcard    14.3    14.3 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24604 
   reportcard    DATABASE     U   CREATE DATABASE reportcard WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE reportcard;
                postgres    false            �            1259    24610    file    TABLE     k   CREATE TABLE public.file (
    id integer NOT NULL,
    name character varying(255),
    salary integer
);
    DROP TABLE public.file;
       public         heap    postgres    false            �            1259    24605    student    TABLE     �  CREATE TABLE public.student (
    rollno integer NOT NULL,
    fullname character varying(50) NOT NULL,
    fathername character varying(50) NOT NULL,
    address character varying(50) NOT NULL,
    dob character varying(50) NOT NULL,
    english double precision NOT NULL,
    hindi double precision NOT NULL,
    maths double precision NOT NULL,
    science double precision NOT NULL,
    social double precision NOT NULL,
    percentage double precision NOT NULL
);
    DROP TABLE public.student;
       public         heap    postgres    false            �          0    24610    file 
   TABLE DATA           0   COPY public.file (id, name, salary) FROM stdin;
    public          postgres    false    210   �
       �          0    24605    student 
   TABLE DATA           �   COPY public.student (rollno, fullname, fathername, address, dob, english, hindi, maths, science, social, percentage) FROM stdin;
    public          postgres    false    209          l           2606    24614    file file_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.file
    ADD CONSTRAINT file_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.file DROP CONSTRAINT file_pkey;
       public            postgres    false    210            j           2606    24609    student student_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.student
    ADD CONSTRAINT student_pkey PRIMARY KEY (rollno);
 >   ALTER TABLE ONLY public.student DROP CONSTRAINT student_pkey;
       public            postgres    false    209            �   S   x�3��*��L-�H��442 .SΌ�̼Ģ��DNC��1�*#LU�މ���Y@qN��9�_bNfX��Ā�ӈ+F��� ��K      �   ~  x��SMo�0=;�b�`��$�}�w�u��!%m�SJ���g
m��IU5�,����r�^~��sUe�n�k�@.���sP %he	ƀ�L��(��N	����æ��Cꫧ�
���T�&��[ϩc���8��-2����hcI��e������[x~y}��� �@J]�`-*Fjh}L���n��P�|䵯6�������2�7��˔0�����'�����m|;�;�w�Lڑ�u�L���Ug�d�K��hF'�%j-x=&�b��:ą������CZjk�Dc���m0s.�|2����dayH~͹����K����4����L�Ղ�`&�������v��^2Y��bI��H#>2!�/ z�y     