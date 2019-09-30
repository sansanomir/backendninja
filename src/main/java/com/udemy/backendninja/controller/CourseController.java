package com.udemy.backendninja.controller;

import com.udemy.backendninja.Model.Person;
import com.udemy.backendninja.service.CourseService;
import com.udemy.backendninja.entity.Course;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private static final String COURSES_VIEW = "courses";
    private static final String COURSES_EDIT = "courseedit";
    private static final Log LOG = LogFactory.getLog(CourseController.class);

    @Autowired
    @Qualifier("courseServiceImpl")
    private CourseService courseService;

    @GetMapping("/listcourses")
    public ModelAndView listAllCourses(){
        LOG.info("method: listAllCourses()");
        ModelAndView mav = new ModelAndView(COURSES_VIEW);
        mav.addObject("course",new Course());
        mav.addObject("courses",courseService.listAllCourses());
        return mav;
    }

    @PostMapping("/addcourse")
    public ModelAndView addCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult){
        LOG.info("medhod: addcourse() " + course);
        ModelAndView mav = new ModelAndView(COURSES_VIEW);
        if(bindingResult.hasErrors()){
            mav.setViewName(COURSES_EDIT);
        }
        else{
            mav.setViewName(COURSES_VIEW);
            mav.addObject("course",course);
            LOG.info("TEMPLATE: " + COURSES_VIEW + "DATA: " + course);
        }
        return mav;
    }

    @GetMapping("/updatecourse/{id}")
    public ModelAndView showUpdateCourse(@PathVariable("id") int id){
        LOG.info("medhod: showUpdateCourse() id: " + id);
        ModelAndView mav = new ModelAndView(COURSES_EDIT);
        mav.addObject("course",courseService.getCourse(id));
        return mav;
    }

    @PostMapping("/updatecoursen/{id}")
    public String updateCourse(@PathVariable("id") int id, @ModelAttribute("course") Course course){
        LOG.info("medhod: updateCourse() id: " + id + course.getId());
        courseService.updateCourse(course);
        return "redirect:/courses/listcourses";
    }

    @GetMapping("/removecourse/{id}")
    public String removeCourse(@PathVariable("id") int id){
        LOG.info("medhod: removeCourse() " + id);
        courseService.removeCourse(id);
        return "redirect:/courses/listcourses";
    }
}
