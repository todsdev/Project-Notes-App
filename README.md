<!-- # Title -->
# Notes
![Demo](https://media.discordapp.net/attachments/655489748885831713/1054487080958439564/Sem_Titulo.png)


<!-- # Short Description -->

>- The user can add **notes** with *title*, **content** and *background color*
>- The note can easily be deleted and undo in case of mistake
>- All the **notes** can be *reorder* to easily find the specific one you want

Application developed for studying purposes, mainly for design it with a clean archicture and jetpack compose. 


<!-- # Badges -->
<div style="display: inline_block"><br>
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/androidstudio/androidstudio-original.svg">
    <img height="30" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg">
</div>

---

# Tags

`Android Studio` `Kotlin` `Jetpack Compose` `Coroutines` `MVVM` `Retrofit` `Hilt` `Clean Architecture`

---


# Demo

![](https://media.discordapp.net/attachments/655489748885831713/1054721767547076658/main_functionalities.gif)
>- Work with all the functionalities of the app easily, such as *add*, *remove*, *edit* and *reorder* your notes


![](https://media.discordapp.net/attachments/655489748885831713/1054721766494326815/add_note.gif)
>- Create your notes with *title* and *content*, but also the **note** background *color* personalized


![](https://media.discordapp.net/attachments/655489748885831713/1054721767131848845/edit_note.gif)
>- The user can easily *edit* the **note**, changing all it's aspects

![](https://media.discordapp.net/attachments/655489748885831713/1054721766070681600/reorder.gif)
>- *Reorder* the notes however it is necessary


---

# Code Example
```kotlin
@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.onBackground
        ))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}
```

The code shows a basic radio button creation using *jetpack compose*. It allows the coder to create the user interface basically just by codes. 
All the interfaces of the app was built in this design. 

---

# Libraries

>- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
>- [Coroutines](https://developer.android.com/kotlin/coroutines?hl=pt-br)
>- [KTX](https://developer.android.com/kotlin/ktx)
>- [Retrofit](https://square.github.io/retrofit/)
>- [OkHTTP](https://square.github.io/okhttp/)
>- [Navigation Components](https://developer.android.com/guide/navigation)
>- [Hilt](https://dagger.dev/hilt/)
>- [ROOM](https://developer.android.com/jetpack/androidx/releases/room?hl=pt-br)
>- [Jetpack Compose](https://developer.android.com/jetpack/compose?hl=pt-br)

---

# Contributors

- [Thiago Rodrigues](https://www.linkedin.com/in/tods/)
