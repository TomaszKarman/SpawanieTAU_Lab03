package tomaszkarman.com;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import tomaszkarman.com.domain.SpawarkaCTR;
import tomaszkarman.com.service.CRUDSpawarki;

public class SpawarkaTestMock {

    private CRUDSpawarki crudSpawarki = new CRUDSpawarki();
    private CRUDSpawarki mockedCrudSpawarki;

    @Before
    public void setUp() throws ParseException {

        mockedCrudSpawarki = mock(CRUDSpawarki.class);

        SpawarkaCTR spawarka1 = new SpawarkaCTR("Spawarka1", "model1", 11, false);
        spawarka1.setCreateTime(crudSpawarki.dateConverterFromString("24-11-2018 16:45:50"));
        spawarka1.setModifyTime(crudSpawarki.dateConverterFromString("24-11-2018 16:46:12"));
        spawarka1.setReadTime(crudSpawarki.dateConverterFromString("24-11-2018 16:49:53"));

        SpawarkaCTR spawarka2 = new SpawarkaCTR("Spawarka2", "model2", 22, false);
        SpawarkaCTR spawarka3 = new SpawarkaCTR("Spawarka3", "model3", 33, false);

        when(mockedCrudSpawarki.getListaSpawarek()).thenReturn(Arrays.asList(spawarka1, spawarka2, spawarka3));
    }

    @Test
    public void checkReadDate() throws ParseException {

        Date currentDate = crudSpawarki.dateConverterFromString("24-11-2018 16:50:14");
        SpawarkaCTR spawarka1 = new SpawarkaCTR("Spawarka1", "model1", 11, false);
        spawarka1.setCreateTime(crudSpawarki.dateConverterFromString("24-11-2018 16:45:50"));
        spawarka1.setReadTime(currentDate);

        when(mockedCrudSpawarki.spawarkaCTRWithReadDate("Spawarka1")).thenReturn(spawarka1);

        assertNotEquals(mockedCrudSpawarki.getListaSpawarek().get(0).getReadTime(), currentDate);
        assertEquals(mockedCrudSpawarki.spawarkaCTRWithReadDate("Spawarka1").getReadTime(), currentDate);
    }

    @Test
    public void addNewObjectWithDate() throws ParseException {

        Date currentDate = crudSpawarki.dateConverterFromString("24-11-2018 16:50:14");
        SpawarkaCTR spawarka1 = new SpawarkaCTR("Spawarka1", "model1", 11, false);
        spawarka1.setCreateTime(currentDate);

        when(mockedCrudSpawarki.addSpawarkaDate(any(SpawarkaCTR.class))).thenReturn(spawarka1);
        assertEquals(mockedCrudSpawarki.addSpawarkaDate(new SpawarkaCTR("Spawarka1", "model1", 11, false)).getCreateTime(), currentDate);
    }

    @Test
    public void updateObjectWithUpdateDate() throws ParseException {

        Date currentDate = crudSpawarki.dateConverterFromString("24-11-2018 16:50:14");
        SpawarkaCTR spawarka = new SpawarkaCTR("SpawarkaNewName", "model1", 11, false);
        spawarka.setModifyTime(currentDate);

        when(mockedCrudSpawarki.updateNameOfObject("Spawarka1", "SpawarkaNewName")).thenReturn(spawarka);
        assertEquals(mockedCrudSpawarki.updateNameOfObject("Spawarka1", "SpawarkaNewName").getName(), "SpawarkaNewName");
        assertEquals(mockedCrudSpawarki.updateNameOfObject("Spawarka1", "SpawarkaNewName").getModifyTime(), currentDate);
    }

    @Test
    public void checkAllDates() throws ParseException {

        SpawarkaCTR spawarka1 = new SpawarkaCTR("Spawarka1", "model1", 11, false);
        spawarka1.setCreateTime(crudSpawarki.dateConverterFromString("24-11-2018 16:45:50"));
        spawarka1.setModifyTime(crudSpawarki.dateConverterFromString("24-11-2018 16:46:12"));
        spawarka1.setReadTime(crudSpawarki.dateConverterFromString("24-11-2018 16:49:53"));

        when(mockedCrudSpawarki.spawarkaCTRWithReadDate("Spawarka1")).thenReturn(spawarka1);

        assertNotNull(mockedCrudSpawarki.spawarkaCTRWithReadDate("Spawarka1").getCreateTime());
        assertNotNull(mockedCrudSpawarki.spawarkaCTRWithReadDate("Spawarka1").getReadTime());
        assertNotNull(mockedCrudSpawarki.spawarkaCTRWithReadDate("Spawarka1").getModifyTime());
    }

    @Test
    public void multiOptionalTestOfSetDate() throws ParseException {

        SpawarkaCTR toFindOne = new SpawarkaCTR("Spawarka2", "model1", 11, false);
        SpawarkaCTR toFindTwo = new SpawarkaCTR("Spawarka2", "model1", 11, false);
        SpawarkaCTR toFindThree = new SpawarkaCTR("Spawarka2", "model1", 11, false);

        SpawarkaCTR spawarka1 = new SpawarkaCTR("Spawarka1", "model1", 11, false);
        Date createDate = crudSpawarki.dateConverterFromString("24-11-2018 16:45:50");
        spawarka1.setCreateTime(createDate);

        SpawarkaCTR spawarka2 = new SpawarkaCTR("Spawarka2", "model1", 11, false);
        Date readDate = crudSpawarki.dateConverterFromString("24-11-2018 16:45:50");
        spawarka2.setReadTime(readDate);

        SpawarkaCTR spawarka3 = new SpawarkaCTR("Spawarka3", "model1", 11, false);
        Date modifyDate = crudSpawarki.dateConverterFromString("24-11-2018 16:45:50");
        spawarka3.setModifyTime(modifyDate);

        when(mockedCrudSpawarki.multiDateUpdater(toFindOne, true, createDate, false, null, false, null)).thenReturn(spawarka1);
        assertSame(mockedCrudSpawarki.multiDateUpdater(toFindOne, true, createDate, false, null, false, null).getCreateTime(), createDate);

        when(mockedCrudSpawarki.multiDateUpdater(toFindTwo, false, null, true, readDate, false, null)).thenReturn(spawarka2);
        assertSame(mockedCrudSpawarki.multiDateUpdater(toFindTwo, false, null, true, readDate, false, null).getReadTime(), readDate);

        when(mockedCrudSpawarki.multiDateUpdater(toFindThree, false, null, false, null, true, modifyDate)).thenReturn(spawarka3);
        assertSame(mockedCrudSpawarki.multiDateUpdater(toFindThree, false, null, false, null, true, modifyDate).getModifyTime(), modifyDate);
    }


}
