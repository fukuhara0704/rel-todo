package com.example.todo.mapper;

import java.util.List;

import com.example.todo.model.TaskModel;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * t_taskのSQLを書くクラス
 */
@Mapper
public interface TaskMapper {

    /////////////////////////////////////////////
    //                   取得系                          //
    /////////////////////////////////////////////
    // 対象のユーザーの本日のタスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_today_flag = 1  and ( task_status = 0 or task_status = 1) ORDER BY id")
    public List<TaskModel> findByIdTodayTask(@Param("user_id") String userId);

    // 対象のユーザーの全タスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_start_datetime < TO_TIMESTAMP(#{start_today}, 'YYYY-MM-DD HH24:MI:SS') and task_end_datetime > TO_TIMESTAMP(#{end_today}, 'YYYY-MM-DD HH24:MI:SS') ORDER BY id")
    public List<TaskModel> findByIdAllTask(@Param("user_id") String userId, @Param("start_today") String startToday, @Param("end_today") String endToday);

    // 対象のユーザーの全未実施タスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_status = #{taskStatus} ORDER BY id")
    public List<TaskModel> findByIdAllUnfinishTask(@Param("user_id") String userId, @Param("taskStatus") Integer taskStatus);

    // 対象のユーザーの実施済みタスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_status = #{taskStatus} ORDER BY id")
    public List<TaskModel> findbyIdAllDoneTask(@Param("user_id") String userId, @Param("taskStatus") Integer taskStatus);
    
    @Select("Select task_id, task_name, task_memo, task_end_datetime, task_today_flag, to_char(task_created_time, 'yyyy/mm/dd hh24:mm') task_created_time from public.t_task where user_id = #{user_id} and task_id = #{taskId}")
    public List<TaskModel> findByTaskId(@Param("user_id") String userId, @Param("taskId") Integer taskId);

    // 対象のユーザーの全タスクの情報を取得する。
    @Select("Select * from public.t_task where user_id = #{userId} ORDER BY id DESC")
    public List<TaskModel> findbyAllTask(@Param("userId") String userId);

    /////////////////////////////////////////////
    //                   更新系                          //
    /////////////////////////////////////////////

    @Update("update public.t_task set  task_name= #{taskName} where user_id = #{userId} and task_id = #{taskId}" )
    public boolean updatebyTaskId(@Param("userId") String userId,@Param("taskId") Integer taskId,@Param("taskName") String taskName);

    @Update("update public.t_task set  task_memo= #{taskMemo} where user_id = #{userId} and task_id = #{taskId}" )
    public boolean updateMemo(@Param("userId") String userId,@Param("taskId") Integer taskId,@Param("taskMemo") String taskMemo);

    @Update("update public.t_task set  task_today_flag = #{todayTaskFlag} where user_id = #{userId} and task_id = #{taskId}" )
    public boolean updateTodayFlag(@Param("userId") String userId, @Param("taskId") Integer taskId, @Param("todayTaskFlag") Integer todayTaskFlag);

    @Update("update public.t_task set  task_status=2 where user_id = #{userId} and task_id = #{taskId}" )
    public boolean doneTask(@Param("userId") String userId, @Param("taskId") Integer taskId);


    @Update("update public.t_task set  task_priority = #{taskPriority} where user_id = #{userId} and task_id = #{taskId}" )
    public boolean updateImportant(@Param("userId") String userId,@Param("taskId")  Integer taskId, @Param("taskPriority") Integer taskPriority);
    /////////////////////////////////////////////
    //                   削除系                          //
    /////////////////////////////////////////////
    @Delete("delete from public.t_task where user_id = #{userId} and task_id = #{taskId}")
    public boolean deleteTask(@Param("userId") String userId,@Param("taskId") Integer taskId);

    /////////////////////////////////////////////
    //                   登録系                          //
    /////////////////////////////////////////////
    @Insert("Insert INTO public.t_task (user_id, task_name) VALUES (#{userId}, #{taskName});")
    public boolean createTask(@Param("userId") String userId,@Param("taskName") String taskName);

}
