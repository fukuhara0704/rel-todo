package com.example.todo.controllers.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import com.example.todo.model.TaskModel;
import com.example.todo.service.TaskAjaxService;
import com.example.todo.session.UserSession;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodayAjaxController {

    @Autowired
    TaskAjaxService taskAjaxService;

    @Autowired
    protected UserSession userSession;

    @Autowired
    Gson gson;

    @ResponseBody
    @RequestMapping(value = "/getAjaxTask", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String getAjaxTaskData(@RequestBody String json) throws IOException, ServletException {
        System.out.println(json);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);

        String result = jobj.get("task_id").getAsString();
        System.out.println(result);
        Integer taskId = Integer.parseInt(result);
        System.out.println(userSession.getUserId());

        List<TaskModel> targetTask = taskAjaxService.findbyTaskId(userSession.getUserId(), taskId);
        System.out.println(gson.toJson(targetTask));
        return gson.toJson(targetTask);
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjaxTask", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String updateAjaxTaskData(@RequestBody String json) throws IOException, ServletException {
        System.out.println(json);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);

        String taskIdStr = jobj.get("task_id").getAsString();
        String taskName = jobj.get("task_name").getAsString();
        Integer taskId = Integer.parseInt(taskIdStr);
        System.out.println(userSession.getUserId());

        boolean updateTask = taskAjaxService.updatebyTaskId(userSession.getUserId(), taskId, taskName);

        String result = "";
        if (updateTask) {
            result = "????????????";
        } else {
            result = "????????????";
        }
        return gson.toJson(result);
    }

    @ResponseBody
    @RequestMapping(value = "/updateAjaxMemo", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String updateAjaxMemoData(@RequestBody String json) throws IOException, ServletException {
        System.out.println(json);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);

        String taskIdStr = jobj.get("task_id").getAsString();
        String taskMemo = jobj.get("task_memo").getAsString();
        Integer taskId = Integer.parseInt(taskIdStr);
        System.out.println(userSession.getUserId());

        boolean updateTask = taskAjaxService.updateMemo(userSession.getUserId(), taskId, taskMemo);

        String result = "";
        if (updateTask) {
            result = "????????????";
        } else {
            result = "????????????";
        }
        return gson.toJson(result);
    }

    /**
     * ????????????????????????????????????
     * 
     * @param json
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/updateAjaxTodayFlag", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String updateAjaxTodayFlag(@RequestBody String json) throws IOException, ServletException {
        System.out.println(json);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);

        String taskIdStr = jobj.get("task_id").getAsString();
        String todayFlagStr = jobj.get("today_task_flag").getAsString();
        System.out.println(taskIdStr);
        Integer taskId = Integer.parseInt(taskIdStr);
        Integer todayTaskFlag = 0;
        if (todayFlagStr.equals("0")) {
            todayTaskFlag = 1;
        }

        System.out.println(userSession.getUserId());

        boolean updateTask = taskAjaxService.updateTodayFlag(userSession.getUserId(), taskId, todayTaskFlag);

        String result = "";
        if (updateTask) {
            result = "????????????";
        } else {
            result = "????????????";
        }
        return gson.toJson(result);
    }

    /**
     * ??????????????????????????????
     * 
     * @param json
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @ResponseBody
    @RequestMapping(value = "/updateAjaxImportant", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String updateAjaxImportant(@RequestBody String json) throws IOException, ServletException {
        System.out.println(json);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);

        String taskIdStr = jobj.get("task_id").getAsString();
        String taskPriorityStr = jobj.get("task_priority").getAsString();
        System.out.println(taskIdStr);
        Integer taskId = Integer.parseInt(taskIdStr);
        Integer taskPriority = 0;
        if (taskPriorityStr.equals("0")) {
            taskPriority = 1;
        }

        boolean updateTask = taskAjaxService.updateImportant(userSession.getUserId(), taskId, taskPriority);

        String result = "";
        if (updateTask) {
            result = "????????????";
        } else {
            result = "????????????";
        }
        return gson.toJson(result);
    }

}
