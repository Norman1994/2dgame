using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ObstracleEnemy : Monster
{
    private float rate = 5.0f;
    private Bullet bullet;
    private Character ch;
    private SpriteRenderer sprite;
    private int liveCount;

    public AudioClip shootSound;
    AudioSource audioSource;

    private bool attack;

    private Vector3 direction;

    protected override void Awake()
    {
        sprite = GetComponentInChildren<SpriteRenderer>();
        bullet = Resources.Load<Bullet>("Bullet");
        //ch = new Character(); 
    }

    protected override void Start()
    {
        audioSource = GetComponent<AudioSource>();
        liveCount = 5;
        InvokeRepeating("Attack", 6.0f, 2.0f);
    }

    // Update is called once per frame
    protected override void Update()
    {

    }

    private void Shoot()
    {
        Vector3 position = transform.position;
        position.y += 0.0f;
        position.x += 4.0f;
        Bullet newBullet = Instantiate(bullet, position, bullet.transform.rotation) as Bullet;

        newBullet.Parent = gameObject;
        newBullet.Direction = newBullet.transform.right;
    }

    public void Attack()
    {
        Debug.Log(Character.status);
        if (Character.status == "Самец")
        {
            Shoot();
            audioSource.PlayOneShot(shootSound, 0.3f);
        }
    }

    protected override void OnTriggerEnter2D(Collider2D collider)
    {
        Bullet bullet = collider.gameObject.GetComponent<Bullet>();

        if (bullet && bullet.Parent != gameObject)
        {
            liveCount--;
            if (liveCount == 0)
                ReceiveDamage();
        }
    }
}
