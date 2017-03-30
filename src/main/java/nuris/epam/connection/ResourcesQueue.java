package nuris.epam.connection;

import nuris.epam.connection.exception.ResourcesException;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Кастомный класс-список , для хранений потоковых обектов
 *
 * @author Kalenov Nurislam
 */
public class ResourcesQueue<T> {
    /**
     * Поле  - ограничение доступа к ресурсу.
     */
    private Semaphore semaphore;
    /**
     * Поле  - хранение списка инициализированных коннектов
     */
    private Queue<T> resource = new ConcurrentLinkedQueue<T>();
    /**
     * Поле  - время ожидание особождение коннекта
     */
    private int timeOut;

    /**
     * Создает новый объект с Semophore и присваеает значение для timeOut
     *
     * @param count   - счетчик семафора
     * @param timeOut - время ожидание коннекта
     */
    public ResourcesQueue(int count, int timeOut) {
        semaphore = new Semaphore(count, true);
        this.timeOut = timeOut;
    }

    /**
     * Метод котрый позволяет брать соедение из списка если оно доступно
     *
     * @return иницилизированный коннект
     */
    public T takeResource() throws ResourcesException {
        try {
            if (semaphore.tryAcquire(timeOut, TimeUnit.SECONDS)) {
                T res = resource.poll();
                return res;
            }
        } catch (InterruptedException e) {
            throw new ResourcesException("You didn't wait for connect", e);
        }
        throw new ResourcesException("You didn't wait for connect");
    }

    /**
     * Возвращяет коннект обратно в списокк инизиализирванных коннекто
     */
    public void returnResource(T res) {

        resource.add(res);
        semaphore.release();
    }

    /**
     * Добавляет инициализированные соедение в список
     */
    public void addResource(T t) {
        resource.add(t);
    }

    /**
     * Размер списка
     *
     * @return размер списка
     */
    public int size() {
        return resource.size();
    }

    /**
     * Список соеденеий
     *
     * @return возвращает список
     */
    public Queue<T> getResources() {
        return resource;
    }
}


