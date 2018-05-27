package com.zgm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedImage bi=new BufferedImage(88, 32, BufferedImage.TYPE_INT_RGB);
		Graphics g=bi.getGraphics();
		Color color=new Color(200, 150, 255);
		g.setColor(color);
        g.fillRect(0,0,88, 32);	
        
        char[] ch="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random r=new Random();
        int len=ch.length,index;
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<4;i++){
        	index=r.nextInt(len);
        	g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
        	g.drawString(ch[index]+"", (i*22)+3, 23);
        	sb.append(ch[index]);
        }
        request.getSession().setAttribute("code", sb.toString());
        //System.out.println(sb);
        ImageIO.write(bi, "JPG", response.getOutputStream());
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
