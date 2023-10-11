package com.openrun.ticket.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.openrun.ticket.board.BoardVO;

@Controller
@RequestMapping("/product/admin")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    // 클래스에서 변수는 생성만 해주고 다른곳(생성자)에서 값을 주는게 좋다.
    private int boardNum;
    private List<BoardVO> boardList;
    
    
    public BoardController() {
        super();
        boardNum = 1;
        boardList = new ArrayList<>();
      
    }
 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }
 
    public void doProcess(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
 
 
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        System.out.println("command = " + command);
        
        //응답 페이지
        String page = "";
        // 맨 밑 페이지 이동시 쓰는거.
        boolean isRedirect = false;
        
        if(command.equals("/inquiryProductList"))
        {
            request.setAttribute("list", boardList);
            
            page = "inquiry_product_list.jsp";
        }
        
        //글쓰기 페이지로 이동
        if(command.equals("/regBoardForm.do"))
        {
            page="inquiry_product_form.jsp";
        }
        
        //글 등록 실행
        if(command.equals("/regBoard.do"))
        {
            //데이터 받기
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String writer = request.getParameter("writer");
            String createDate = request.getParameter("createDate");
            
            //위에서 전달받은 데이터를 갖는 게시글 생성
            BoardVO board = new BoardVO(boardNum, title, content, writer, createDate);
            // BoardDTO의 첫번쨰 boardNum = 글번호
            boardNum++;
            
            
            
            boardList.add(board);
            
            //page="boardList.jsp";
            page ="boardList.do";
            // 맨 밑 페이지 이동시 쓰는거. 일단 page이동 do로 끝내는것만. 기억하자.
            isRedirect = true;
        }
        
        //게시글 상세보기
        if(command.equals("/inquiryProductAnswer"))
        {
            int num = Integer.parseInt(request.getParameter("boardNum"));
            
            //게시글 목록에서 넘겨온 글번호와 같은 게시글 찾기.
            // boardList로 반복할거다. 반복해서 뽑으면 그게 BoardDTO.
            for(BoardVO board: boardList) {
                if(board.getBoardNum() == num) {
                    request.setAttribute("board", board );
                }
            }
            
            page="inquiry_product_answer.jsp";
        }
        
        //게시글 삭제하기
        if(command.equals("/delete.do"))
        {
            //boardNum이라는 문자열을 받아온다. int형 변환을 함께해준다.
            int num = Integer.parseInt(request.getParameter("boardNum"));
            
            for(int i =0; i<boardList.size(); i++)
            {
                //리스트 뒤에 get은 순번. 
                if(boardList.get(i).getBoardNum() == num )
                {
                    boardList.remove(i);
                    
                }
            }
            
            
            
            //page="boarList.jsp"; 이렇게 들고가면 데이터 안가져가준다. 화며네 암것도안봉미.
            page="boarList.do";
        }
        
        //게시글 수정 페이지로 이동
        if(command.equals("/updateBoardForm.do"))
        {
            int num = Integer.parseInt(request.getParameter("boardNum"));
            
            for(BoardVO board : boardList) {
                if(board.getBoardNum() == num) {
                    request.setAttribute("board", board);
                }
            }
            //수정하고자 하는 게시글의 정보를 jsp에 보내줘야함.
            page = "update_board_form.jsp";
        }
        
        //글 수정
        if(command.equals("/updateBoard.do"))
        {
            String title = request.getParameter("title");
            String writer = request.getParameter("writer");
            String createDate = request.getParameter("createDate");
            String content = request.getParameter("content");
            int num = Integer.parseInt(request.getParameter("boardNum"));
            
            for(BoardVO board : boardList) {
                if(board.getBoardNum()==num)
                {
                    board.setTitle(title);
                    board.setWriter(writer);
                    board.setContent(content);
                    board.setCreateDate(createDate);
                }
            }
            
            page="boarList.do";
            //page = "boardDetail.do?boardNum=" + num; 왜 이거지?
        }
        
        // 페이지 이동.
        if(isRedirect) {
            response.sendRedirect(page);
        }
        else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        
    }

}
