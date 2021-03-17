package mx.uniprotec.entidad.modelo;

import java.util.Date;

public class LoginSingle {


		
		public String token;
		public Long tiempoInicio;
		public ResultVO resultVO;
		
		
		@Override
		public String toString() {
			return "LoginSingle [token=" + token + ", tiempoInicio=" + tiempoInicio + ", resultVO=" + resultVO + "]";
		}
		
		public LoginSingle(String token, Long tiempoInicio, ResultVO resultVO) {
			super();
			this.token = token;
			this.tiempoInicio = tiempoInicio;
			this.resultVO = resultVO;
		}

		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public Long getTiempoInicio() {
			return tiempoInicio;
		}
		public void setTiempoInicio(Long tiempoInicio) {
			this.tiempoInicio = tiempoInicio;
		}

		public ResultVO getResultVO() {
			return resultVO;
		}

		public void setResultVO(ResultVO resultVO) {
			this.resultVO = resultVO;
		}

		
		
		
		
		
	}