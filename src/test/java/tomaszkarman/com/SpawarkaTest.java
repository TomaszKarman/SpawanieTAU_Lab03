package tomaszkarman.com;

import tomaszkarman.com.domain.*;
import tomaszkarman.com.service.*;

import org.junit.Before;
import org.junit.Test;
import tomaszkarman.com.domain.SpawarkaCTR;
import tomaszkarman.com.service.CRUDSpawarki;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class SpawarkaTest {


        CRUDSpawarki crud = new CRUDSpawarki();

        @Before
        public void before() {

                SpawarkaCTR spawarka1 = new SpawarkaCTR("Dunlop", "TIG", 1234, false);
                SpawarkaCTR spawarka2 = new SpawarkaCTR("Avanger", "TIG", 1235, false);
                spawarka1.setCreateTime(new Date());

                crud.listaSpawarek.add(spawarka1);
                crud.listaSpawarek.add(spawarka2);

                try {
                        TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                spawarka2.setCreateTime(new Date());
        }



        @Test
        public void doesListaSpawarekExists() {
                assertNotNull(crud);
        }

        @Test
        public void checkDateExist() {
                assertNotNull(crud.getByID(1234).getCreateTime());
        }

        @Test
        public void checkCreateDates() {
                assertNotEquals(crud.getByID(1234).getCreateTime(), crud.getByID(1235).getCreateTime());
        }

        //Test dla kodu bez sleep przy tworzeniu obiektow
        /*
        @Test
        public void checkCreateDates1() {
                assertEquals(crud.getByID(1234).getCreateTime().getTime(), crud.getByID(1235).getCreateTime().getTime());
        }
        */

        @Test
        public void checkModifyDateNotExists() {
                assertNull(crud.getByID(1234).getModifyTime());
        }

        @Test
        public void howManyElement() {
                assertEquals(2, crud.listaSpawarek.size());
        }

        @Test
        public void getByID(){
                //assertNotNull (crud.listaSpawarek.get(0));
                assertNotNull(crud.getByID(1234));
        }


        @Test(expected = IllegalArgumentException.class)
        public void deletingElement() {
                crud.removeSpawarka(crud.getByID(1235));
                 SpawarkaCTR s2 = crud.getByID(1235);
        }


        @Test
        public void renameElement() {
                crud.renameSpawarka(1234, "Zmiana");
                assertEquals("Zmiana",crud.getByID(1234).getName());
        }

        @Test
        public void checkReadDates() {
                assertNotNull(crud.getByID(1234).getReadTime());
        }

        @Test
        public void checkModifyDateExists() {
                crud.renameSpawarka(1234, "Oooops");
                assertNotNull(crud.getByID(1234).getModifyTime());
        }



}


