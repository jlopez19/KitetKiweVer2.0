package com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveluno;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fup.jennyferlopez.proyectokitetkiwe.R;
import com.fup.jennyferlopez.proyectokitetkiwe.fragments.niveldos.Nivel2Activity;
import com.fup.jennyferlopez.proyectokitetkiwe.gestorbd.GestorBd;
import com.fup.jennyferlopez.proyectokitetkiwe.models.Puntos;
import com.fup.jennyferlopez.proyectokitetkiwe.utils.Preference;

import java.util.List;

public class VocalAImagenActivity extends AppCompatActivity {
    SharedPreferences preferences;
    String avatarSeleccionado, userName;
    TextView tv_puntos;
    ImageView icAvatarNiveles;
    TextView tv_title;
    GestorBd db;
    ImageView v_na_a, v_na_e, v_na_i, v_na_u, v_as_a, v_as_e, v_as_i, v_as_u, img_tabaco, img_sabila, img_rojo, img_blanco, img_aguacate, img_aguila;

    private int modificarX=0;
    private int modificary=0;
    boolean detectView;
    float x, x1, y, y1, h,h1,l1, l;
    float xi, xi1, yi, yi1, hi,hi1, li, li1;
    float xm, xm1, ym, ym1, hm,hm1, lm, lm1;
    float xg, xg1, yg, yg1, hg,hg1, lg, lg1;
    float xh, xh1, yh, yh1, hh,hh1, lh, lh1;
    float xj, xj1, yj, yj1, hj,hj1, lj, lj1;
    float temp_x, temp_y;
    int cont_intentos=0, cont_good=0, cont_fail=0, id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocal_aimagen);

        v_na_a= (ImageView) findViewById(R.id.v_na_a);
        v_na_e= (ImageView) findViewById(R.id.v_na_e);
        v_na_i= (ImageView) findViewById(R.id.v_na_i);
        v_na_u= (ImageView) findViewById(R.id.v_na_u);
        v_as_a= (ImageView) findViewById(R.id.v_as_a);
        v_as_e= (ImageView) findViewById(R.id.v_as_e);
        v_as_i= (ImageView) findViewById(R.id.v_as_i);
        v_as_u= (ImageView) findViewById(R.id.v_as_u);
        img_tabaco= (ImageView) findViewById(R.id.img_conejo);
        img_sabila= (ImageView) findViewById(R.id.img_sabila);
        img_rojo= (ImageView) findViewById(R.id.img_rojo);
        img_blanco= (ImageView) findViewById(R.id.img_blanco);
        img_aguacate= (ImageView) findViewById(R.id.img_aguacate);
        img_aguila= (ImageView) findViewById(R.id.img_rosado);

        v_na_a.setOnTouchListener(handlerMover);
        v_na_a.setOnLongClickListener(detect);

        v_na_e.setOnTouchListener(handlerMover);
        v_na_e.setOnLongClickListener(detect);

        v_na_i.setOnTouchListener(handlerMover);
        v_na_i.setOnLongClickListener(detect);

        v_na_u.setOnTouchListener(handlerMover);
        v_na_u.setOnLongClickListener(detect);

        v_as_a.setOnTouchListener(handlerMover);
        v_as_a.setOnLongClickListener(detect);

        v_as_e.setOnTouchListener(handlerMover);
        v_as_e.setOnLongClickListener(detect);

        v_as_i.setOnTouchListener(handlerMover);
        v_as_i.setOnLongClickListener(detect);

        v_as_u.setOnTouchListener(handlerMover);
        v_as_u.setOnLongClickListener(detect);

        db=new GestorBd(getApplication());
        tv_title = (TextView) findViewById(R.id.tv_title);
        icAvatarNiveles = (ImageView) findViewById(R.id.ic_avatarNiveles);
        tv_puntos = (TextView) findViewById(R.id.tv_puntos);
        String font_url ="font/dklemonyellowsun.otf";
        Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
        tv_puntos.setTypeface(font);
        tv_title.setTypeface(font);
        loadPreference();
        cargarTextV();
    }

    private void loadPreference() {
        preferences = getSharedPreferences(Preference.PREFERENCE_NAME, Activity.MODE_PRIVATE);
        avatarSeleccionado = preferences.getString(Preference.AVATAR_SEECCIONADO, "");
        userName =preferences.getString(Preference.USER_NAME, "");

        if (avatarSeleccionado.equals(null)) {
            icAvatarNiveles.setBackgroundResource(Integer.parseInt(null));
        } else if (avatarSeleccionado.equals("1")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_uno_n);
        } else if (avatarSeleccionado.equals("2")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_dos_n);
        } else if (avatarSeleccionado.equals("3")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nino_tres_n);
        } else if (avatarSeleccionado.equals("4")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_uno_n);
        } else if (avatarSeleccionado.equals("5")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_dos_n);
        } else if (avatarSeleccionado.equals("6")) {
            icAvatarNiveles.setBackgroundResource(R.drawable.nina_tres_n);
        }
    }
    private void cargarTextV() {
        id_user =db.obtenerId(userName);
        List<Puntos> pts=db.sumaPuntos(id_user);
        pts=db.sumaPuntos(id_user);
        int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
        tv_puntos.setText(""+ p);
    }

    View.OnLongClickListener detect= new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            detectView=true;
            return false;
        }
    };
    View.OnTouchListener handlerMover=new View.OnTouchListener(){
        @Override
        public boolean onTouch(View v, MotionEvent motionEvent) {
            PointF DownPT= new PointF();
            PointF StartPT= new PointF();

            xi=img_tabaco.getX();
            yi=img_tabaco.getY();
            hi=img_tabaco.getWidth();
            li=img_tabaco.getHeight();

            x=img_sabila.getX();
            y=img_sabila.getY();
            h=img_sabila.getWidth();
            l=img_sabila.getHeight();

            xm=img_rojo.getX();
            ym=img_rojo.getY();
            hm=img_rojo.getWidth();
            lm=img_rojo.getHeight();

            xg=img_blanco.getX();
            yg=img_blanco.getY();
            hg=img_blanco.getWidth();
            lg=img_blanco.getHeight();

            xh=img_aguacate.getX();
            yh=img_aguacate.getY();
            hh=img_aguacate.getWidth();
            lh=img_aguacate.getHeight();

            xj=img_aguila.getX();
            yj=img_aguila.getY();
            hj=img_aguila.getWidth();
            lj=img_aguila.getHeight();
            int eid= motionEvent.getAction();

            switch (eid){
                case MotionEvent.ACTION_MOVE:
                    if (detectView){
                        StartPT = new PointF(v.getX(), v.getY());
                        PointF mv=new PointF(motionEvent.getX()-DownPT.x, motionEvent.getY()-DownPT.y);
                        v.setX((StartPT.x+mv.x)-modificarX);
                        v.setY((StartPT.y+mv.y)-modificary);
                    }

                    break;
                case MotionEvent.ACTION_DOWN:
                    DownPT.x=motionEvent.getX();
                    DownPT.y=motionEvent.getY();
                    int contT=0;
                    if (contT==0) {
                        int id=v.getId();
                        if (id==R.id.v_na_a){
                            temp_x = DownPT.x=v_na_a.getX();
                            temp_y = DownPT.y=v_na_a.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_na_e){
                            temp_x = DownPT.x=v_na_e.getX();
                            temp_y = DownPT.y=v_na_e.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_na_i){
                            temp_x = DownPT.x=v_na_i.getX();
                            temp_y = DownPT.y=v_na_i.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_na_u){
                            temp_x = DownPT.x=v_na_u.getX();
                            temp_y = DownPT.y=v_na_u.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_as_a){
                            temp_x = DownPT.x=v_as_a.getX();
                            temp_y = DownPT.y=v_as_a.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_as_e){
                            temp_x = DownPT.x=v_as_e.getX();
                            temp_y = DownPT.y=v_as_e.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_as_i){
                            temp_x = DownPT.x=v_as_i.getX();
                            temp_y = DownPT.y=v_as_i.getY();
                            contT=contT+1;
                        }else if (id==R.id.v_as_u){
                            temp_x = DownPT.x=v_as_u.getX();
                            temp_y = DownPT.y=v_as_u.getY();
                            contT=contT+1;
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:

                    switch (v.getId()){
                        case R.id.v_as_a:
                            x1=v.getX();
                            y1=v.getY();
                            h1=v.getWidth();
                            l1=v.getHeight();
                            if ((x>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1)) || ((x+h)>=x1 && x<=(x1+h1) && y >= y1 && y <= (y1+l1))
                                    || (x>=x1 && x<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1)) || ((x+h)>=x1 && (x+h)<=(x1+h1) && (y+l) >= y1 && (y+l) <= (y1+l1))){
                                toastSuccess();
                                v_as_a.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                v_as_a.setX(temp_x);
                                v_as_a.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.v_na_a:
                            xi1=v.getX();
                            yi1=v.getY();
                            hi1=v.getWidth();
                            if ((xi>=xi1 && xi<=(xi1+hi1) && yi >= yi1) || ((xi+hi)>=xi1 && xi<=(xi1+hi1) && yi >= yi1 )
                                    || (xi>=xi1 && x<=(xi1+hi1)) || ((xi+hi)>=xi1 && (xi+hi)<=(xi1+hi1))){
                                toastSuccess();
                                v_na_a.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                v_na_a.setX(temp_x);
                                v_na_a.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.v_as_e:
                            xm1=v.getX();
                            ym1=v.getY();
                            hm1=v.getWidth();
                            if ((xm>=xm1 && xm<=(xm1+hm1) && ym >= ym1) || ((xm+hm)>=xm1 && xm<=(xm1+hm1) && ym >= ym1 )
                                    || (xm>=xm1 && xm<=(xm1+hm1)) || ((xm+hm)>=xm1 && (xm+hm)<=(xm1+hm1))){
                                toastSuccess();
                                v_as_e.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                v_as_e.setX(temp_x);
                                v_as_e.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.v_as_i:
                            xg1=v.getX();
                            yg1=v.getY();
                            hg1=v.getWidth();
                            if ((xg>=xg1 && xg<=(xg1+hg1) && yg >= yg1) || ((xg+hg)>=xg1 && xg<=(xg1+hg1) && yg >= yg1 )
                                    || (xg>=xg1 && xg<=(xg1+hg1)) || ((xg+hg)>=xg1 && (xg+hg)<=(xg1+hg1))){
                                toastSuccess();
                                v_as_i.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                v_as_i.setX(temp_x);
                                v_as_i.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.v_as_u:
                            xh1=v.getX();
                            yh1=v.getY();
                            hh1=v.getWidth();
                            if ((xh>=xh1 && xh<=(xh1+hh1) && yh >= yh1) || ((xh+hh)>=xh1 && xh<=(xh1+hh1) && yh >= yh1 )
                                    || (xh>=xh1 && xh<=(xh1+hh1)) || ((xh+hh)>=xh1 && (xh+hh)<=(xh1+hh1))){
                                toastSuccess();
                                v_as_u.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                v_as_u.setX(temp_x);
                                v_as_u.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.v_na_u:
                            xj1=v.getX();
                            yj1=v.getY();
                            hj1=v.getWidth();
                            if ((xj>=xj1 && xj<=(xj1+hj1) && yj >= yj1) || ((xj+hj)>=xj1 && xj<=(xj1+hj1) && yj >= yj1 )
                                    || (xj>=xj1 && xj<=(xj1+hj1)) || ((xj+hj)>=xj1 && (xj+hj)<=(xj1+hj1))){
                                toastSuccess();
                                v_na_u.setVisibility(View.INVISIBLE);
                                cont_good=cont_good+1;
                                cont_intentos=cont_intentos+1;
                            }else{
                                toastFail();
                                v_na_u.setX(temp_x);
                                v_na_u.setY(temp_y);
                                cont_fail=cont_fail+1;
                                cont_intentos=cont_intentos+1;
                            }
                            break;
                        case R.id.v_na_e:
                            v_na_e.setX(temp_x);
                            v_na_e.setY(temp_y);
                            cont_fail=cont_fail+1;
                            cont_intentos=cont_intentos+1;
                            break;
                        case R.id.v_na_i:
                            v_na_i.setX(temp_x);
                            v_na_i.setY(temp_y);
                            cont_fail=cont_fail+1;
                            cont_intentos=cont_intentos+1;
                            break;
                    }

                    detectView=false;
                    break;

                default:
                    break;

            }
            cargarPuntos();
            return false;
        }
        private void cargarPuntos() {
            if (cont_good ==6) {
                Intent irMenu = new Intent(getApplication(), QuizUnoActivity.class);
                startActivity(irMenu);
                finish();
            }if (cont_good==6 && cont_intentos ==6){
                Puntos puntos= new Puntos(id_user, 3);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good==6&& (cont_intentos >6 || cont_intentos <9)){
                id_user =db.obtenerId(userName);
                Puntos puntos= new Puntos(id_user, 2);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good==6 && (cont_intentos >=9 || cont_intentos <=12)){
                id_user =db.obtenerId(userName);
                Puntos puntos= new Puntos(id_user, 1);
                db.insertarPuntos(puntos);
                List<Puntos> pts=db.sumaPuntos(id_user);
                int p=Integer.parseInt(String.valueOf(pts.get(0).getPuntos()));
                tv_puntos.setText(""+ p);
            }else if (cont_good<4 && cont_intentos >12){
               // toastWarning();
            }
        }
};
        private void toastFail() {
            Toast toasta = new Toast(getApplicationContext());
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_personalizado,
                    (ViewGroup) findViewById(R.id.lytLayout));

            TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
            ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
            imgToast.setBackgroundResource(R.drawable.toast_fail);
            txtMsg.setText(R.string.toast_fail);
            String font_url ="font/dklemonyellowsun.otf";
            Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
            txtMsg.setTypeface(font);
            toasta.setDuration(Toast.LENGTH_SHORT);
            toasta.setView(layout);
            toasta.show();
        }

        public void toastSuccess() {
            Toast toasta = new Toast(getApplicationContext());
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_personalizado,
                    (ViewGroup) findViewById(R.id.lytLayout));

            TextView txtMsg = (TextView)layout.findViewById(R.id.tvMsjToast);
            ImageView imgToast =(ImageView) layout.findViewById(R.id.imgToast);
            imgToast.setBackgroundResource(R.drawable.toast_good);
            txtMsg.setText(R.string.col_good);
            String font_url ="font/dklemonyellowsun.otf";
            Typeface font = Typeface.createFromAsset(this.getResources().getAssets(), font_url);
            txtMsg.setTypeface(font);
            toasta.setDuration(Toast.LENGTH_SHORT);
            toasta.setView(layout);
            toasta.show();

        }

    }
