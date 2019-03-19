package cn.dyaoming.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;


/**
 * 功能描述：
 * <P/>
 * 创建时间：2017年2月25日
 * <P/>
 * 创建人： bingran
 * <P/>
 * 修改人：无
 * <P/>
 * 修改时间：无
 * <P/>
 * 修改备注：无
 * 
 */
public class DataBaseFactory
{

	private String tableListSqlString = "select table_name from user_tables";
	private String tableCommentListSqlString = "select comments from user_tab_comments where table_name=?";
	private String columnSqlString = "select column_name,data_type,data_scale from user_tab_columns where table_name=? order by column_name";
	private String columnCommentSqlString = "select column_name,comments from user_col_comments where table_name=? order by column_name";

	String driverName = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@dym270307872.vicp.cc:27072/orcl";
	String user = "MANAGE";
	String password = "MANAGE";

	String createUserName = "DYM";// 创建人
	String createPath = "F://dataBaseTest//";// 保存路径
	String pageageName = "com.zhkj.eapi.rowmapper";// 包名



	/**
	 * 功能描述： 方法
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		new DataBaseFactory().CreateBean();
	}


	/**
	 * 
	 * 功能描述：用户登录方法。
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param name
	 * @return
	 */
	public String captureName(String name)
	{
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}



	/**
	 * 功能描述：用户登录方法。
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 */
	public void CreateBean()
	{

		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

		List<Map<String, String>> tableList = GetMap(tableListSqlString, "");

		for (Integer i = 0; i < tableList.size(); i++)
		{

			String tableNameString = captureName(tableList.get(i).get("TABLE_NAME").toLowerCase());

			if(tableNameString.contains("standby"))
			{
				continue;
			}
			
			System.out.println("开始生成表["+tableNameString+"]的BEAN层");
			
			List<Map<String, String>> commentList = GetMap(tableCommentListSqlString, tableNameString.toUpperCase());

			StringBuilder txtBuilder = new StringBuilder();

			txtBuilder.append("/*\r\n");
			txtBuilder.append(" * @(#)" + tableNameString + ".java 创建于" + sd.format(new Date()) + "\r\n");
			txtBuilder.append(" *\r\n");
			txtBuilder.append(" * Copyright (c) 2017-2019 by Zhkj.\r\n");
			txtBuilder.append(" *\r\n");
			txtBuilder.append("*/\r\n");
			txtBuilder.append("package " + pageageName + ";\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("import java.io.Serializable;\r\n");
			txtBuilder.append("import java.sql.*;\r\n");
			txtBuilder.append("import org.springframework.jdbc.core.RowMapper;\r\n");
			txtBuilder.append("\r\n");

			txtBuilder.append("/**\r\n");
			txtBuilder.append(" *\r\n");
			txtBuilder.append(" * 类名称：" + tableNameString + "\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 类描述： " + commentList.get(0).get("COMMENTS") + " Bean类\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 创建时间：" + sd.format(new Date()) + "\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 创建人： " + createUserName + "\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 修改人：无\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 修改时间：无\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 修改备注：无\r\n");
			txtBuilder.append(" * <P/>\r\n");
			txtBuilder.append(" * 版本：V1.0\r\n");
			txtBuilder.append(" * \r\n");
			txtBuilder.append(" */\r\n");
			txtBuilder.append("public class " + tableNameString + " implements RowMapper<" + tableNameString
					+ ">, Serializable\r\n");
			txtBuilder.append("{\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\tprivate static final long serialVersionUID = 1L;\r\n");
			txtBuilder.append("\r\n");

			List<Map<String, String>> columnList = GetMap(columnSqlString, tableNameString.toUpperCase());
			List<Map<String, String>> columnCommentList = GetMap(columnCommentSqlString, tableNameString.toUpperCase());

			for (Integer j = 0; j < columnList.size(); j++)
			{

				txtBuilder
						.append("\tprivate " + GetType(columnList.get(j), true) + " "
								+ columnList.get(j).get("COLUMN_NAME").toLowerCase() + ";//"
								+ GetDesc(columnCommentList, columnList.get(j).get("COLUMN_NAME")).replace("\r\n", "")
								+ "\r\n");
			}
			txtBuilder.append("\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\r\n");

			txtBuilder.append("\tpublic " + tableNameString + "()\r\n");
			txtBuilder.append("\t{\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\t}\r\n");

			txtBuilder.append("\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\t@Override\r\n");
			txtBuilder.append("\tpublic " + tableNameString
					+ " mapRow(ResultSet rs, int rowNum) throws SQLException\r\n");
			txtBuilder.append("\t{\r\n");
			txtBuilder.append("\t\t" + tableNameString + " " + tableNameString.toLowerCase() + " = new "
					+ tableNameString + "();\r\n");
			txtBuilder.append("\r\n");

			for (Integer j = 0; j < columnList.size(); j++)
			{

				txtBuilder.append("\t\t" + tableNameString.toLowerCase() + ".set"
						+ captureName(columnList.get(j).get("COLUMN_NAME").toLowerCase()) + "(rs.get"
						+ GetType(columnList.get(j), false) + "(\""
						+ columnList.get(j).get("COLUMN_NAME").toLowerCase() + "\"));\r\n");
			}

			txtBuilder.append("\r\n");

			txtBuilder.append("\t\treturn " + tableNameString.toLowerCase() + ";\r\n");
			txtBuilder.append("\t}\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\r\n");
			txtBuilder.append("\r\n");

			for (Integer j = 0; j < columnList.size(); j++)
			{

				txtBuilder.append("\tpublic " + GetType(columnList.get(j), true) + " get"
						+ captureName(columnList.get(j).get("COLUMN_NAME").toLowerCase()) + "()\r\n");
				txtBuilder.append("\t{\r\n");
				txtBuilder.append("\t\treturn " + columnList.get(j).get("COLUMN_NAME").toLowerCase() + ";\r\n");
				txtBuilder.append("\t}\r\n");

				txtBuilder.append("\r\n");
				txtBuilder.append("\r\n");
				txtBuilder.append("\r\n");

				txtBuilder.append("\tpublic void set" + captureName(columnList.get(j).get("COLUMN_NAME").toLowerCase())
						+ "(" + GetType(columnList.get(j), true) + " "
						+ columnList.get(j).get("COLUMN_NAME").toLowerCase() + ")\r\n");
				txtBuilder.append("\t{\r\n");
				txtBuilder.append("\t\tthis." + columnList.get(j).get("COLUMN_NAME").toLowerCase() + " = "
						+ columnList.get(j).get("COLUMN_NAME").toLowerCase() + ";\r\n");
				txtBuilder.append("\t}\r\n");

				txtBuilder.append("\r\n");
				txtBuilder.append("\r\n");
				txtBuilder.append("\r\n");
			}

			txtBuilder.append("}\r\n");

			try
			{
				new DataBaseFactory().CreateFileAndWrite(createPath + tableNameString + ".java", txtBuilder.toString());
			} catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("生成完成！");
	}


	
	
	/**
	 * 功能描述： 方法
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param mapData
	 * @param isPro
	 * @return
	 */
	public String GetType(Map<String, String> mapData, Boolean isPro)
	{
		String dataType = mapData.get("DATA_TYPE").toUpperCase();
		String dataScale = mapData.get("DATA_SCALE");

		switch (dataType)
		{
		case "VARCHAR2":
			return "String";
		case "DATE":
			return "Timestamp";
		case "NUMBER":
			
			if (dataScale == null || dataScale == "" || dataScale.equals("0"))// 整数型
			{
				System.out.println("NUMBER["+mapData.get("COLUMN_NAME")+"]["+dataScale+"][Integer]");
				return isPro ? "Integer" : "Int";
			}
			System.out.println("NUMBER["+mapData.get("COLUMN_NAME")+"]["+dataScale+"][Double]");
			return "Double";
		default:
			return "String";
		}
	}


	
	/**
	 * 功能描述：方法。
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param list
	 * @param columnName
	 * @return
	 */
	public String GetDesc(List<Map<String, String>> list, String columnName)
	{
		columnName = columnName.toUpperCase();
		for (Integer j = 0; j < list.size(); j++)
		{
			//System.out.println("校对值 ：" + columnName + " 当前值：" + list.get(j).get("COLUMN_NAME") + " ");
			if (list.get(j).get("COLUMN_NAME").equals(columnName))
			{
				return StringUtil.processNullString(list.get(j).get("COMMENTS"));
			}
		}
		return "";
	}



	/**
	 * 功能描述： 方法
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param path
	 * @param newStr
	 * @throws IOException
	 */
	public void CreateFileAndWrite(String path, String newStr) throws IOException
	{
		try
		{
			FileOutputStream fos = new FileOutputStream(path);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(newStr);
			osw.flush();
		} catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}



	/**
	 * 功能描述： 方法
	 * <P/>
	 * 创建时间：2017年2月25日
	 * <P/>
	 * 创建人： bingran
	 * <P/>
	 * 修改人：无
	 * <P/>
	 * 修改时间：无
	 * <P/>
	 * 修改备注：无
	 * 
	 * @param sqlString
	 * @param tableName
	 * @return
	 */
	public List<Map<String, String>> GetMap(String sqlString, String tableName)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		// 数据库连接对象
		Connection conn = null;

		try
		{
			// 反射Oracle数据库驱动程序类
			Class.forName(driverName);

			// 获取数据库连接
			conn = DriverManager.getConnection(url, user, password);

			// 创建该连接下的PreparedStatement对象
			pstmt = conn.prepareStatement(sqlString);

			if (tableName != "")
			{
				// 传递第一个参数值 root，代替第一个问号
				pstmt.setString(1, tableName);
			}

			// 执行查询语句，将数据保存到ResultSet对象中
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				Map<String, String> hm = new HashMap<String, String>();
				ResultSetMetaData rsmd = rs.getMetaData();
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++)
				{
					String key = rsmd.getColumnLabel(i);
					String value = rs.getString(i);
					hm.put(key, value);
				}
				list.add(hm);
			}
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
}
