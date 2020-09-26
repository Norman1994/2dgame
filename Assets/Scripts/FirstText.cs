using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FirstText : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetKeyDown("e"))
        {
            Application.LoadLevel("Main");
        }
	}
}
