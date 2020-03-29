package mx.uniprotec.inicio.entity;

public class LoginSingle {


		private static String token;
	    private static ResultVO resultVO;
	    private static LoginSingle ls;
	   

	    public  static LoginSingle getLoginSingle(String token,ResultVO resultVO) {
	    	 if (ls==null) {
	    	 ls =new LoginSingle (token,resultVO);
	    	 }
	    	 return ls;
	    	 }
	    
	 	 private LoginSingle(String token,ResultVO resultVO){
	    	 this.token=token;
	    	 this.resultVO=resultVO;
	    	 }
	 	 
		    public static String getToken() {
				return token;
			}

			public static void setToken(String token) {
				LoginSingle.token = token;
			}

			public static ResultVO getResultVO() {
				return resultVO;
			}

			public static void setResultVO(ResultVO resultVO) {
				LoginSingle.resultVO = resultVO;
			}


	}