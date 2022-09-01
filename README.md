
# Challenge Yape
Este proyecto se desarrolló una aplicación que permitirá visualizar recetas de cocina. 

## Explicación de las ramas creadas
#### Rama **main**
En la rama **main**, he desarrollado una version del código que no consume una api, esto debido a que la herramienta proporcionada para el desarrollado de este proyecto solo está disponible 24 horas, ya que no cuento con una suscripción premiun a dicha plataforma
#### Rama **project_with_api_json**
En esta rama podrá visualizar la version
de la aplicación que esta haciendo uso del [API](https://demo3732690.mockable.io/yapechallenge)

## Solución de Arquitectura
Para la implementación de este proyecto, he utilizado la arquitectura MVVM. Este me permitió desacoplar lo máximo posible la interfaz de usuario de la lógica de la aplicación.

## Patrones de Diseño del Proyecto
He desarrollado este proyecto utilizando, por ejemplo, los siguientes patrones de diseño

### patrones reacionales 
Estos patrones me permitieron delegar la responsabilidad de creación de objetos, de esta manera puede utilizar diversos métodos que aumentaron la flexibilidad y re utilización de código.
Además, me permitieron encapsular las clases y ocultar como se crea e instancía la clase.

Ejemplo:

- Singleton
- Builder
- Factory

## Novedades en la Plataforma
Como funcionalidad adicional, desarrollé un "Activty", que permité visualizar las lista de ingredientes y la cantidad de ellos

## Librerías Externas

He hecho uso de la siguientes librerías

```bash
  annotationProcessor 'com.github.bumptech.glide:compiler:4.13.2'
  implementation 'com.github.bumptech.glide:glide:4.13.2'
  implementation 'com.google.android.gms:play-services-maps:18.1.0'
  implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1'
  implementation "com.squareup.retrofit2:retrofit:2.9.0"
  implementation "com.squareup.retrofit2:converter-gson:2.9.0"
```

## Configuración Inicial
Para el correcto funcionamiento del mapa, es necesario agregar su **APIKEY**
- [Configure su APIKEY](https://cloud.google.com/?utm_source=google&utm_medium=cpc&utm_campaign=latam-PE-all-es-dr-BKWS-all-all-trial-e-dr-1011454-LUAC0010196&utm_content=text-ad-none-any-DEV_c-CRE_512364917036-ADGP_Hybrid%20%7C%20BKWS%20-%20EXA%20%7C%20Txt%20~%20GCP_General-KWID_43700062784667371-kwd-301173107424&utm_term=KW_google%20cloud-ST_Google%20Cloud&gclid=CjwKCAjwsMGYBhAEEiwAGUXJaR_BapHKSXcxSmp7RVYItmH1yieJrnxmDZnZNTe3avGCGgLaK0bgphoCkj4QAvD_BwE&gclsrc=aw.ds)
Una vez haya configurado su APIKEY, añadala en la siguiente ruta: **ChallengeYape\app\src\main\res\values\google_maps_api.xml**
```kotlin
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="google_maps_key" formatted="false" templateMergeStrategy="preserve" translatable="false">AÑADIR AQUÍ SU APIKEY</string>
</resources>
}
```
