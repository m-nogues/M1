/**
 * @file tp4.c
 *
 * @section desc File description
 *
 *
 * @section copyright Copyright
 *
 *
 * @section infos File informations
 *
 * $Date$ mar. mars  7 16:10:37 CET 2017
 * $Rev$
 * $Author$ 14000806
 * $URL$ /private/student/6/06/14000806/M1git/M1/str/tp4
 */

#include "tpl_os.h"
#include "nxt_motors.h"
#include "ecrobot_interface.h"
#include "ecrobot_private.h"

FUNC(int, OS_APPL_CODE) main(void)
{
    StartOS(OSDEFAULTAPPMODE);
    ShutdownOS(E_OK);
    return 0;
}

// Déclaration des tâches et de l'alarme pour la tâche 2.
DeclareTask(task1);
DeclareTask(task2);
DeclareAlarm(a1);

TASK(task1)
{
  // Initialisation du sonar.
  ecrobot_init_sonar_sensor(NXT_PORT_S2);
  TerminateTask();
}

TASK(task2)
{
  // Récupération de la distance à l'objet.
  int distanceO = ecrobot_get_sonar_sensor(NXT_PORT_S2);

  // Calcul de la vitesse des moteurs (en fonction de la distance restante
  // jusqu'à l'objet).
  int vitesse = distanceO - 20;
  if (vitesse > 100)
    vitesse = 100;
  else if (vitesse < 20 && vitesse >= 0)
    vitesse = 20;
  else if (vitesse > -20 && vitesse <= 0)
    vitesse = -20;

  // Vérification de la distance restante jusqu'à l'objet et application de la
  // vitesse correspondante.
  if (distanceO < 19 || distanceO > 21){
    ecrobot_set_motor_speed(NXT_PORT_A, vitesse);
    ecrobot_set_motor_speed(NXT_PORT_B, vitesse);
  } else {
    ecrobot_set_motor_speed(NXT_PORT_A, 0);
    ecrobot_set_motor_speed(NXT_PORT_B, 0);
  }

  TerminateTask();
}

ISR(isr_button_start)
{
    ecrobot_status_monitor("isr_button_start");

}

ISR(isr_button_stop)
{
    ShutdownOS(E_OK);
}

ISR(isr_button_left)
{
    ecrobot_status_monitor("isr_button_left");

}

ISR(isr_button_right)
{
    ecrobot_status_monitor("isr_button_right");

}

/* End of file tp4.c */
