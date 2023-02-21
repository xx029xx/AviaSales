package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.AviaSales;
import ru.netology.repository.AviaSalesRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AviaSalesManagerTest {
    AviaSalesRepository repository = new AviaSalesRepository();
    AviaSalesManager manager = new AviaSalesManager(repository);
    AviaSales one = new AviaSales(1, 1_290, 40, "ABA", "SCO");
    AviaSales two = new AviaSales(2, 2_990, 50, "ABA", "AKX");
    AviaSales three = new AviaSales(3, 3_990, 60, "ABA", "GZP");
    AviaSales fourth = new AviaSales(4, 4_990, 70, "ABA", "ALA");
    AviaSales fifth = new AviaSales(5, 5_990, 80, "ABA", "ATH");
    AviaSales sixth = new AviaSales(6, 6_990, 90, "ABA", "KQT");

    @BeforeEach
    public void setup() {
        repository.save(one);
        repository.save(two);
        repository.save(three);
        repository.save(fourth);
        repository.save(fifth);

    }

    @Test //Сортировка стоимости билета по возрастанию
    public void sortingTicketPriceTest() {
        AviaSales[] expected = new AviaSales[]{one, two, three};
        AviaSales[] actual = new AviaSales[]{one, two, three};
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Добавление еще одного элемента
    public void addingElementTest() {
        repository.save(sixth);
        AviaSales[] actual = repository.findAll();
        AviaSales[] expected = new AviaSales[]{one, two, three, fourth, fifth, sixth};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Сортировка по времени
    public void sortingTimeTest() {
        AviaSales[] actual = manager.findAll("ABA", "SCO");
        AviaSales[] expected = new AviaSales[]{one};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(expected));
    }

    @Test // Неверный аэропорт вылета
    public void incorrectDepartureAirportFromTest() {
        AviaSales[] actual = manager.findAll("MVR", "GZP");
        AviaSales[] expected = new AviaSales[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test // Неверный аэропорт прилета
    public void incorrectDepartureAirportToTest() {
        AviaSales[] actual = manager.findAll("ABA", "MVR");
        AviaSales[] expected = new AviaSales[]{};
        assertArrayEquals(expected, actual);
        System.out.println(Arrays.toString(actual));
    }

    @Test // Удаление одного элемента
    public void removeElementTest() {
        repository.removeById(4);
        assertEquals(repository.findAll().length, 4);
    }

    @Test // Сохранить элементы
    public void saveElementsTest() {
        assertEquals(repository.findAll().length, 5);
    }
}