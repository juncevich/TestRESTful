package entities;

/**
 * В классе описана сущность поля БД.
 * Используется в определении первичного ключа.
 */
public class Field {
    /**
     * Название поля.
     */
    private String name;
    /**
     * Поле содержит признак первичного
     * ключа.
     * true - поле является
     * первиным ключом.
     * false - поле не является
     * первичным ключом.
     */
    private Boolean isPrime;

    /**
     * Метод возвращает имя поля.
     *
     * @return имя сущности.
     */
    public final String getName() {
        return name;
    }

    /**
     * Метод устанавливает имя сущности.
     *
     * @param fieldName Имя сущности.
     */
    public final void setName(final String fieldName) {
        this.name = fieldName;
    }

    /**
     * Метод возвращает признак первичного
     * ключа.
     *
     * @return true - поле является
     * первиным ключом.
     * false - поле не является
     * первичным ключом.
     */
    public final Boolean getPrime() {
        return isPrime;
    }

    /**
     * Метод устанавливает признак первичного ключа.
     * Если поле содержит значение "PRI", то
     * поле является первичным ключом.
     *
     * @param value если у поля присутствует
     *              аттрибут "PRI", то аттрибут передается,
     *              если отсутствует, то передается
     *              либо пустое значение, либо другой аттрибут.
     */
    public final void setPrime(final String value) {
        if (value.equals("PRI")) {
            isPrime = true;
        } else {
            isPrime = false;
        }

    }


}
