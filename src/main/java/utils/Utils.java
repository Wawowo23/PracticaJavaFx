package utils;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class Utils {

    public static String extraeFecha(LocalDateTime horaFichaje) {
        return "'" + horaFichaje.getYear() +
                "-" + horaFichaje.getMonthValue() +
                "-" + horaFichaje.getDayOfMonth() + "'";
    }

    public static String extraeHora(LocalDateTime horaFichaje) {
        return "'" + horaFichaje.getHour() +
                ":" + horaFichaje.getMinute() +
                ":" + horaFichaje.getSecond() + "'";
    }

    public static LocalDateTime formateaFechaHora(Date fecha, Time hora) {
        return LocalDateTime.of(fecha.toLocalDate().getYear(),
                fecha.toLocalDate().getMonthValue(),fecha.toLocalDate().getDayOfMonth(),
                hora.getHours(),hora.getMinutes(),hora.getSeconds());
    }
}
