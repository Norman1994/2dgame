using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FinalScene : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        
            if (Input.GetKeyDown("m"))
            {
                Application.LoadLevel("Menu");
            
        }
    }
}
