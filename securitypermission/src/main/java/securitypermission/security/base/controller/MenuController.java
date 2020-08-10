package securitypermission.security.base.controller;

import entity.Menu;
import entity.PageData;
import methods.Backtrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import securitypermission.security.base.service.MenuService;
import vo.ResultVO;

/**
 * 菜单管理
 * @author wqy
 * @version 1.0
 * @date 2020/6/9 16:35
 */
@Controller
@RequestMapping(value = "/mct")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/addMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO addMenu(String parentCode, String menuName, String router, String imgsrc){

        try {
            String rs = menuService.addMenu(parentCode,menuName,router,imgsrc);

            if(rs.equals("success")){
                return Backtrack.success(true);
            }

           return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }


    }


    @RequestMapping(value = "/updateMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO updateMenu(String menuCode,String menuName,String router,String imgsrc){

        try {
            Integer rs = menuService.updateMenu(menuCode,menuName,router,imgsrc);
            if(rs>0){
                return Backtrack.success(true);
            }
            return Backtrack.errot("操作失败");
        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }
    }

    @RequestMapping(value = "/deleteMenu",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO deleteMenu(String menuCode){

        try {
            Integer rs = menuService.deleteMenu(menuCode);
            if(rs>0){
                return Backtrack.success(true);
            }
            return Backtrack.errot("请先解除绑定");
        }catch (Exception e){
            e.printStackTrace();
            return Backtrack.errot("操作失败");
        }
    }

    /**
     * 分页获取菜单
     * @param currentPage 页码‘
     * @return
     */
    @RequestMapping(value = "/getMenuMsgs",method = {RequestMethod.POST})
    @ResponseBody
    public ResultVO<PageData<Menu>> getMenuMsgs(Integer currentPage){

        PageData<Menu> list = menuService.getMenuMsgs(currentPage);

        if(null==list){
            return Backtrack.errot("操作失败");
        }

        return Backtrack.success(list,"操作成功");

    }

}
