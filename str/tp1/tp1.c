/**
 * @file tp1.c
 *
 * @section desc File description
 *
 *
 * @section copyright Copyright
 *
 *
 * @section infos File informations
 *
 * $Date$ mar. janv. 31 17:01:36 CET 2017
 * $Rev$
 * $Author$ 14000806
 * $URL$ /private/student/6/06/14000806/M1git/M1/str/tp1
 */

#include "tpl_os.h"
#include "nxt_motors.h"
#include "ecrobot_interface.h"
#include "ecrobot_private.h"

int displayY = 0;

update()
{
  display_update();
  displayY = (displayY+1)%7;
  display_goto_xy(0,displayY);

}

FUNC(int, OS_APPL_CODE) main(void)
{
    StartOS(OSDEFAULTAPPMODE);
    ShutdownOS(E_OK);
    return 0;
}

DeclareTask(task0);
DeclareTask(task1);

PreTaskHook()
{
  TaskType task;
  GetTaskID(&task);
  display_int(task,0);
  update();


}

PostTaskHook()
{
  TaskType task;
  GetTaskID(&task);
  display_int(task,0);
  update();

}

TASK(task0)
{
  display_string("debut");
  update();
  ActivateTask(task1);
  display_string("fin");
  update();
  TerminateTask();
}

TASK(task1)
{
    //ecrobot_status_monitor("Bonjour le monde !");
    display_string("Bonjour le monde !");
    update();
    TerminateTask();
    //while(1){};

    //ChainTask(task1);
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

/* End of file tp1.c */
