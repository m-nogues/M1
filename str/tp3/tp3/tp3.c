/**
 * @file tp3.c
 *
 * @section desc File description
 *
 *
 * @section copyright Copyright
 *
 *
 * @section infos File informations
 *
 * $Date$ mar. févr. 28 16:15:30 CET 2017
 * $Rev$
 * $Author$ 14000806
 * $URL$ /private/student/6/06/14000806/M1git/M1/str/tp3/tp3
 */

#include "tpl_os.h"
#include "nxt_motors.h"
#include "ecrobot_interface.h"
#include "ecrobot_private.h"


int periodeNum = 0, distanceO = 255;

FUNC(int, OS_APPL_CODE) main(void)
{
    StartOS(OSDEFAULTAPPMODE);
    ShutdownOS(E_OK);
    return 0;
}

DeclareTask(task1);
DeclareTask(task2);
DeclareTask(task3);
DeclareTask(task4);
DeclareResource(distance);
DeclareAlarm(a1);
DeclareAlarm(a3);
DeclareAlarm(a2);


TASK(task1)
{
    ecrobot_init_sonar_sensor(NXT_PORT_S2);
    TerminateTask();
}
//collision
TASK(task2)
{
    int collisionR  = ecrobot_get_touch_sensor(NXT_PORT_S1);
    int collisionL  = ecrobot_get_touch_sensor(NXT_PORT_S4);

    if (collisionL || collisionR) {
      GetResource(distance);
      distanceO = 0;
      ReleaseResource(distance);
    }

    TerminateTask();
}

//sensor sonar
TASK(task3)
{
    GetResource(distance);
    distanceO = ecrobot_get_sonar_sensor(NXT_PORT_S2);
    ReleaseResource(distance);
    TerminateTask();
}

//engines
TASK(task4)
{
  periodeNum = (periodeNum + 1) % 10;

  GetResource(distance);
  if (distanceO == 0){
    //marche arriere
    ecrobot_set_motor_speed(NXT_PORT_A, -50);
    ecrobot_set_motor_speed(NXT_PORT_B, -50);
  } else if(distanceO < 50){
    //faire tourner le robot
    ecrobot_set_motor_speed(NXT_PORT_A, 50);
    ecrobot_set_motor_speed(NXT_PORT_B, -50);
  } else if (periodeNum == 0){
    int random = (ecrobot_get_sonar_sensor(NXT_PORT_S2) % 100) +1;
    ecrobot_set_motor_speed(NXT_PORT_A, random);
    ecrobot_set_motor_speed(NXT_PORT_B, -random);
  } else {
    ecrobot_set_motor_speed(NXT_PORT_A, 50);
    ecrobot_set_motor_speed(NXT_PORT_B, 50);
  }
  ReleaseResource(distance);
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

/* End of file tp3.c */
