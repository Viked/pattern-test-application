package viked.weathermap.model.decorators;

/**
 * Created by viked on 27.05.16.
 */
public class DecoratorItemSettings {

    private Boolean checked;

    private Integer id;

    private Integer titleId;

    public DecoratorItemSettings() {
    }

    public DecoratorItemSettings(Boolean checked, Integer id, Integer titleId) {
        this.checked = checked;
        this.id = id;
        this.titleId = titleId;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTitleId() {
        return titleId;
    }
}
