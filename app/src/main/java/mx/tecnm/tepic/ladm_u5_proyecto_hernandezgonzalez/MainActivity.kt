package mx.tecnm.tepic.ladm_u5_proyecto_hernandezgonzalez

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_DENIED
import android.location.Location
import android.location.LocationManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker.PERMISSION_DENIED
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import io.grpc.Status.PERMISSION_DENIED
import mx.tecnm.tepic.ladm_u5_proyecto_hernandezgonzalez.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var baseRemota = FirebaseFirestore.getInstance()
    var posicion = ArrayList<Data>()
    lateinit var locacion: LocationManager
    lateinit var binding: ActivityMainBinding
    lateinit var actionBar: ActionBar
    lateinit var MyModelList: ArrayList<MyModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }

        baseRemota.collection("centro")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    return@addSnapshotListener
                }

                var resultado = ""
                for (document in value!!) {
                    var data = Data()
                    data.nombre = document.getString("nombre").toString()
                    data.posicion1 = document.getGeoPoint("posicion1")!!
                    data.posicion2 = document.getGeoPoint("posicion2")!!
                    resultado += data.toString()
                    posicion.add(data)
                }

            }

        locacion = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var oyente = Oyente(this)
        locacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 01f, oyente)

    }
    private fun miUbicacion() {
        LocationServices.getFusedLocationProviderClient(this)
            .lastLocation.addOnSuccessListener {
                var geoPosicion = GeoPoint(it.latitude,it.longitude)
                for (item in posicion){
                    if (item.estoyEn(geoPosicion)){

                    }
                }
            }.addOnFailureListener {
            }
    }

    fun templo() {
        binding.card1.visibility = View.VISIBLE
        binding.card2.visibility = View.VISIBLE
        binding.card3.visibility = View.VISIBLE
        binding.card4.visibility = View.VISIBLE

        binding.banner1.setImageResource(R.drawable.templo1)
        binding.title1.setText("")

        binding.banner2.setImageResource(R.drawable.templo2)
        binding.title2.setText("")

        binding.banner3.setImageResource(R.drawable.templo3)
        binding.title3.setText("")

        binding.banner4.setImageResource(R.drawable.templo4)
        binding.title4.setText("")

        AlertDialog.Builder(this)
            .setMessage("Te encuentras en el Templo expiatorio del carmen en Tepic")
            .setTitle("ATENCION")
            .setPositiveButton("OK"){p,q->}
            .show()
    }

    fun cafe() {
        binding.card1.visibility = View.VISIBLE
        binding.card2.visibility = View.VISIBLE
        binding.card3.visibility = View.VISIBLE
        binding.card4.visibility = View.VISIBLE

        binding.banner1.setImageResource(R.drawable.cafe1)
        binding.title1.setText("Logo del lugar")

        binding.banner2.setImageResource(R.drawable.cafe2)
        binding.title2.setText("")

        binding.banner3.setImageResource(R.drawable.cafe3)
        binding.title3.setText("Vista a las mesas")

        binding.banner4.setImageResource(R.drawable.cafe4)
        binding.title4.setText("Otra vista a las mesas")

        var audio = MediaPlayer.create(this,R.raw.cafeaudio)
        audio.start()
        AlertDialog.Builder(this)
            .setMessage("Te encuentras en el Cafe Diligencias")
            .setTitle("ATENCION")
            .setPositiveButton("OK"){p,q->}
            .show()
    }

    fun palacio() {
        binding.card1.visibility = View.VISIBLE
        binding.card2.visibility = View.VISIBLE
        binding.card3.visibility = View.VISIBLE
        binding.card4.visibility = View.VISIBLE

        binding.banner1.setImageResource(R.drawable.palacio1)
        binding.title1.setText("Vista frontal")

        binding.banner2.setImageResource(R.drawable.palacio2)
        binding.title2.setText("Foto de 1971")

        binding.banner3.setImageResource(R.drawable.palacio3)
        binding.title3.setText("Fotografia antigua")

        binding.banner4.setImageResource(R.drawable.palacio4)
        binding.title4.setText("Foto de 1967")

        var audio = MediaPlayer.create(this,R.raw.palacioaudio)
        audio.start()

        AlertDialog.Builder(this)
            .setMessage("Te encuentras en Palacio de Gobierno")
            .setTitle("ATENCION")
            .setPositiveButton("OK"){p,q->}
            .show()
    }

    fun plaza() {
        binding.card1.visibility = View.VISIBLE
        binding.card2.visibility = View.VISIBLE
        binding.card3.visibility = View.VISIBLE
        binding.card4.visibility = View.VISIBLE

        binding.banner1.setImageResource(R.drawable.plaza1)
        binding.title1.setText("fotografia tomada en 2008")

        binding.banner2.setImageResource(R.drawable.plaza2)
        binding.title2.setText("Fotografia mas reciente")

        binding.banner3.setImageResource(R.drawable.plaza3)
        binding.title3.setText("Desmantelacion del obelisco")

        binding.banner4.setImageResource(R.drawable.plaza4)
        binding.title4.setText("Plaza cuando era conocida como Jardin de Sanroman")

        var audio = MediaPlayer.create(this,R.raw.plazaaudio)
        audio.start()

        AlertDialog.Builder(this)
            .setMessage("Te encuentras en la Plaza Bicentenario")
            .setTitle("ATENCION")
            .setPositiveButton("OK"){p,q->}
            .show()
    }

    fun hotel() {
        binding.card1.visibility = View.VISIBLE
        binding.card2.visibility = View.VISIBLE
        binding.card3.visibility = View.VISIBLE
        binding.card4.visibility = View.VISIBLE

        binding.banner1.setImageResource(R.drawable.hotel1)
        binding.title1.setText("")

        binding.banner2.setImageResource(R.drawable.hotel2)
        binding.title2.setText("Fotografia frontal")

        binding.banner3.setImageResource(R.drawable.hotel3)
        binding.title3.setText("Suite")

        binding.banner4.setImageResource(R.drawable.hotel4)
        binding.title4.setText("Lobby")

        var audio = MediaPlayer.create(this,R.raw.hotelaudio)
        audio.start()

        AlertDialog.Builder(this)
            .setMessage("Te encuentras en el Hotel Real de Don Juan")
            .setTitle("ATENCION")
            .setPositiveButton("OK"){p,q->}
            .show()
    }
}

class Oyente(puntero:MainActivity) :android.location.LocationListener{
    var p = puntero

    override fun onLocationChanged(p0: Location) {
        var geoPosicionGPS = GeoPoint(p0.latitude,p0.longitude)

        for (item in p.posicion){
            if (item.estoyEn(geoPosicionGPS)){
                if (item.nombre.equals("templo")) {
                    p.binding.textView3.setText("Templo expiatorio del carmen en Tepic")
                    p.templo()
                }else if(item.nombre.equals("plaza")){
                    p.binding.textView3.setText("Plaza Bicentenario")
                    p.plaza()
                }
                else if(item.nombre.equals("hotel")){
                    p.binding.textView3.setText("Hotel Real de Don Juan")
                    p.hotel()
                }
                else if(item.nombre.equals("palacio")){
                    p.binding.textView3.setText("Palacio de Gobierno")
                    p.palacio()
                }
                else if(item.nombre.equals("cafe")){
                    p.binding.textView3.setText("Cafeteria Diligencias")
                    p.cafe()
                }else{
                    p.binding.textView3.setText("Buscando...")
                    p.binding.card1.visibility = View.GONE
                    p.binding.card2.visibility = View.GONE
                    p.binding.card3.visibility = View.GONE
                    p.binding.card4.visibility = View.GONE
                }
            }
        }
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

}