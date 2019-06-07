# AndroidButtonDrawableTintCompat

### Read First
This library use AndroidX if you face problems about dependecies conflict, try **Jetifier**.

### Installation
Use Jitpack (fix later)

### Usage
use attribute `app:buttonDrawableTintCompat_drawable_tint` to set button drawable tint
```
<com.taweewong.buttondrawabletintcompat.ButtonDrawableTintCompat
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:padding="16dp"
    android:text="Hello World!"
    android:drawableStart="@mipmap/ic_launcher"
    android:drawablePadding="16dp"
    app:buttonDrawableTintCompat_drawable_tint="#55FFFFFF"/>
```

and also work with color selector

```
<com.taweewong.buttondrawabletintcompat.ButtonDrawableTintCompat
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:padding="16dp"
    android:text="Hello World!"
    android:drawableStart="@mipmap/ic_launcher"
    android:drawablePadding="16dp"
    app:buttonDrawableTintCompat_drawable_tint="@color/color_list"/>
```
### Limitation
Once you set the drawable tint color, all of drawable in button will change (LEFT, TOP, UP, DOWN) and maybe have some problems with image (that is not vector) please feel free to fork and check Proterdeff mode in class ButtonDrawableTintCompat
