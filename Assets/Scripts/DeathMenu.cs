using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DeathMenu : MonoBehaviour {

	void OnGUI()
	{
		if(GUI.Button(new Rect(Screen.height/2, Screen.width/2 + 50,500,50), "Да, я хочу надрать ещё пару задниц!")) 
		{
			Application.LoadLevel ("Main"); 
		}
	}
}
