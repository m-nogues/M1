/**
 * @file tp2.c
 *
 * @section desc File description
 *
 *
 * @section copyright Copyright
 *
 *
 * @section infos File informations
 *
 * $Date$ mar. févr.  7 16:19:17 CET 2017
 * $Rev$
 * $Author$ 14000806
 * $URL$ /private/student/6/06/14000806/M1git/M1/str/tp2
 */

#include "tpl_os.h"
#include "nxt_motors.h"
#include "ecrobot_interface.h"
#include "ecrobot_private.h"

int displayY = 0, i = 0;

FUNC(int, OS_APPL_CODE) main(void)
{
    StartOS(OSDEFAULTAPPMODE);
    ShutdownOS(E_OK);
    return 0;
}

void update()
{
  display_update();
  displayY = (displayY+1)%7;
  display_goto_xy(0,displayY);

}

DeclareTask(task0);
DeclareTask(task1);
DeclareAlarm(a1);

TASK(task0){
  /*q1.1
    SetAbsAlarm(a1,0,1000);
    ChainTask(task0);
  */

  //q1.3
  ActivateTask(task1);

  TerminateTask();
}

TASK(task1)
{
    TaskType task;

    SetAbsAlarm(a1, i++ * 1000, 0);

    GetTaskID(&task);
    display_int(task,0);
    display_string(" ");
    display_int(ecrobot_get_systick_ms(),0);
    update();

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

/* End of file tp2.c */
